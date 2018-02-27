using System;
using System.Collections.Generic;
using System.IO;
using System.Net.Sockets;
using LoggerLib;
using System.Net.NetworkInformation;



namespace ChatLib {

    public class Client {

        TcpClient client;
        NetworkStream stream;
        public event MessageReceivedEventHandler MessageReceived;
        public event ChangedConnectionStatusEventHandler ChangedConnectionStatus;
        Logger logger = new Logger();


        private List<string> message = new List<string>();

        /// <summary>
        /// Returns the message, client side
        /// </summary>
        public List<string> GetMessage() {
            return message;
        }


        //connection status variables
        private bool connected;

        /// <summary>
        /// Returns the connection status, client side
        /// </summary>
        public bool IsConnected() {
            return connected;
        }

        /// <summary>
        /// Attempts to connect to the server.
        /// </summary>
        /// <param name="server">ipAddress</param>
        /// <param name="port">Port number</param>
        public void Connect(String server, String port) {

            try {

                client = new TcpClient(server, Int32.Parse(port));
                stream = client.GetStream();
                connected = true;
                logger.CreateNewLogFile();

            }
            catch (ArgumentNullException e) {
                connected = false;
            }
            catch (ArgumentOutOfRangeException e) {
                connected = false;
            }
            catch (SocketException e) {
                connected = false;
            }
        }

        /// <summary>
        /// Stops the connection client side, closes the stream, and sets connection bool to false.
        /// </summary>
        public void Disconnect() {
            try {
                WriteMessage("****Client Has Disconnected****");
                client.Close();
                stream.Close();
                connected = false;
                logger.AppendToLog("***Client has disconnected***");
            }
            catch (SocketException e) { }
            catch (ArgumentNullException e) { }
            catch (NullReferenceException e) { }
        }

        // TODO: Geth this working
        /// <summary>
        /// Pings server to test if it is open
        /// </summary>
        public void PingServer() {
            while (connected) {
                var ping = new Ping();
                var reply = ping.Send("127.0.0.1"); 
                if (reply.Status == IPStatus.Success) {
                    connected = true;
                }
                else {
                    connected = false;
                    break;
                }
            }
        }

        /// <summary>
        /// Checks the stream for messages. Saves them to a list
        /// </summary>
        public void CheckForMessages() {

            int numBytes;
            Byte[] bytes = new Byte[256];
            message.Clear();

            try {
                if (stream.DataAvailable) {  
                    numBytes = stream.Read(bytes, 0, bytes.Length);
                    string msg = System.Text.Encoding.ASCII.GetString(bytes, 0, numBytes);
                    message.Add(msg);
                }
            }
            catch (ArgumentException e) { }
            catch (IOException e) { }
            catch (ObjectDisposedException e) { }
        }

        /// <summary>
        /// Writes the input to the stream and delivers.
        /// </summary>
        /// <param name="message"></param>
        public void WriteMessage(string message) {
            try {

                Byte[] data = System.Text.Encoding.ASCII.GetBytes(message);
                stream.Write(data, 0, data.Length);
                if (!message.Equals("****Client Has Disconnected****")) {
                    logger.AppendToLog("Client:  " + message);
                }
                
            }
            catch (ArgumentException e) { }
            catch (IOException e) { }
            catch (SocketException e) { }
            catch (NullReferenceException e) { }
            catch (ObjectDisposedException e) { }
        }

        /// <summary>
        /// Checks the state of the server, is it up or down
        /// </summary>
        /// <returns>boolean</returns>
        public bool CheckServerState() {

            // bool conState = client.Client.Poll(01, SelectMode.SelectRead);
            bool conState = client.Client.Poll(01, SelectMode.SelectError);
            return conState;
        }

        /// <summary>
        /// Checks for messages from the server
        /// </summary>
        public void ClientChat() {
            while (IsConnected()) {
                CheckForMessages();
                List<string> message = GetMessage();
                if (message != null) {
                    foreach (string msg in message) {
                        MessageReceived(this, new MessageReceivedEventArgs("Server:  " + msg + "\n"));
                        logger.AppendToLog("Server:  " + msg);
                    }//end for
                }
                //if (!CheckServerState()) {
                //    connected = false;
                //    ChangedConnectionStatus(this, new ChangedConnectionStatusEventArgs(false));
                //}
            }//end while

        }//end ClientChat

    }//end class
}//end namespace

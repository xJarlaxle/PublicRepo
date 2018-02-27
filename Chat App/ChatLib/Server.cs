using System;
using System.Collections.Generic;
using System.Net;
using System.Net.Sockets;
using System.IO;
using LoggerLib;

namespace ChatLib {

    public class Server {

        TcpListener server;
        TcpClient client;
        Logger logger = new Logger();
        NetworkStream stream;
        private List<string> message = new List<string>();
        
        /// <summary>
        /// Returns the message, client side
        /// </summary>
        public List<string> GetMessage() {
            return message;
        }

        // variables for ipAddress and port
        private const String ipAddress = "127.0.0.1";
        private const int port = 13000;
        
        //server status
        private bool started;
        private bool connected;

        /// <summary>
        /// Returns the value of the private boolean started(server side).
        /// </summary>
        public bool Started() {
            return started;
        }

        /// <summary>
        /// Starts up the server and sets the started bool to true
        /// </summary>
        public void StartUpServer() {

            try {
                server = new TcpListener(IPAddress.Parse(ipAddress), port);
                // Start listening for client requests.
                server.Start();
                started = true;
            }
            catch (SocketException e) {
                started = false;
            }
        }

        /// <summary>
        /// Stops the server side, closes the stream, and sets connection bool to false.
        /// </summary>
        public void Disconnect() {

            try {
                server.Stop();
                stream.Close();
                connected = false;
            }
            catch (SocketException e) { }
            catch (IOException e) { }
        }

        /// <summary>
        /// Tells the server to wait for a client connection.
        /// </summary>
        public void FindConnection() {
            try {
                client = server.AcceptTcpClient();
                stream = client.GetStream();
                connected = true;
            }
            catch (SocketException e) {
                connected = false;
            }
            catch (InvalidOperationException e) {
                connected = false;
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
        /// Writes the input to the stream and delivers
        /// </summary>
        /// <param name="message">String message</param>
        public void WriteMessage(string message) {
            try {
                Byte[] data = System.Text.Encoding.ASCII.GetBytes(message);
                stream.Write(data, 0, data.Length);
                stream.Flush();
            }
            catch (ArgumentException e) { }
            catch (IOException e) { }
            catch (SocketException e) { }
        }

    }//end class Server
}//end namespace ChatLib


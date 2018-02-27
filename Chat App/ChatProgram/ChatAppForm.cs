using ChatLib;
using System;
using System.Threading;
using System.Windows.Forms;


namespace ChatProgram {
    public partial class ChatAppForm : Form {

        Client client = new Client();
        Thread workerThread;
        Thread pingThread;
        bool connected = false, connectedAlready = false;
        
        /// <summary>
        /// Form initialization
        /// </summary>
        public ChatAppForm() {

            client.MessageReceived += new MessageReceivedEventHandler(Client_MessageReceived);
            client.ChangedConnectionStatus += new ChangedConnectionStatusEventHandler(Client_ConnectionStatusChanged);

            InitializeComponent();
        }

        /// <summary>
        /// Event function that alerts if the server is disconnected
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="connection"></param>
        private void Client_ConnectionStatusChanged(object sender, ChangedConnectionStatusEventArgs connection) {
            if (!connection.Connected) {
                if (InvokeRequired) {
                    MethodInvoker updateMethod = new MethodInvoker(delegate () {
                        conversationTxtBox.AppendText("********** SERVER HAS DISCONNECTED **********");
                        sendBtn.Enabled = false;
                    });
                    conversationTxtBox.BeginInvoke(updateMethod);
                }
                else {
                    conversationTxtBox.AppendText("********** SERVER HAS DISCONNECTED **********");
                    sendBtn.Enabled = false;
                }//end else
            }
            else if(connection.Connected) {

            }

        }

        /// <summary>
        /// Event function that alerts when a message is received
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="msg"></param>
        private void Client_MessageReceived(object sender, MessageReceivedEventArgs msg) {
            if (conversationTxtBox.InvokeRequired) {
                MethodInvoker myMethod = new MethodInvoker(delegate () {
                    conversationTxtBox.AppendText(msg.message);
                });
                conversationTxtBox.BeginInvoke(myMethod);
            }
            else {
                conversationTxtBox.AppendText(msg.message);
            }
        }

        /// <summary>
        /// Connect button on the UI menu, sets in motion the connection and initializes threads
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void connectToolStripMenuItem_Click(object sender, EventArgs e) {
            client.Connect("127.0.0.1", "13000");
            if (client.IsConnected()) {

                sendBtn.Enabled = true;
                connected = true;
                connectedAlready = true;
                disconnectToolStripMenuItem.Enabled = true;
                connectToolStripMenuItem.Enabled = false;
                conversationTxtBox.Clear();

                try {
                    workerThread = new Thread(client.ClientChat);
                    workerThread.Name = "myWorkerThread";
                    workerThread.Start();

                    //pingThread = new Thread(client.PingServer);
                    //pingThread.Name = "myPingThread";
                    //pingThread.Start();
                }catch (ArgumentNullException) { }
                
            }//end if
        }
            
        /// <summary>
        /// Send button on the UI, sends the message and displays it on the UI
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void sendBtn_Click(object sender, EventArgs e) {
            string message = sendMessageTxtBx.Text;
            if (message.Length > 0) {
                client.WriteMessage(message);
                sendMessageTxtBx.Clear();
                conversationTxtBox.AppendText(">>:  " + message + "\n");
            }
        }

        /// <summary>
        /// Exit button on the UI menu, disconnects the UI from the server, closes the threads carefully then closes the window
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void exitToolStripMenuItem_Click(object sender, EventArgs e) {
            try {
                sendBtn.Enabled = false;
                client.Disconnect();
                if (workerThread != null) {
                    workerThread.Join();
                }
                //pingThread.Join();
                System.Windows.Forms.Application.Exit();
            }
            catch (NullReferenceException q) { }
            catch (ThreadStateException q) { }
            catch (ThreadInterruptedException q) { }
        }

        /// <summary>
        /// Disconnects the UI from the server, closes threads carefully
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void disconnectToolStripMenuItem_Click(object sender, EventArgs e) {
            client.Disconnect();
            workerThread.Join();
            //pingThread.Join();
            sendBtn.Enabled = false;
            disconnectToolStripMenuItem.Enabled = false;
            connectToolStripMenuItem.Enabled = true;
        }

        /// <summary>
        /// Called on the UI form loading, disables the send button and the Disconnect menu item
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void ChatAppForm_Load(object sender, EventArgs e) {
            sendBtn.Enabled = false;
            disconnectToolStripMenuItem.Enabled = false;
        }

        /// <summary>
        /// Called on the form closing, calls on the exitToolStripMenuItem function
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void ChatAppForm_FormClosing(object sender, FormClosingEventArgs e) {
            exitToolStripMenuItem_Click(sender, e);
        }


    }
}

using ChatLib;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ServerChat {
    class Program {

        /// <summary>
        /// Starts the server
        /// </summary>
        /// <param name="args"></param>
        static void Main(string[] args) {

            Server server = new ChatLib.Server();
            server.StartUpServer();
            if (server.Started()) {
                Console.Write("Waiting for connection...");
                //wait for a connection from the client
                server.FindConnection();
                Console.WriteLine("Client Received!");
                Console.WriteLine();

                ServerChat(server);

            }
        }

        /// <summary>
        /// Handles the displaying and sending of messages for the server
        /// </summary>
        /// <param name="server"></param>
        static void ServerChat(Server server) {
            while (true) {
                if (!Console.KeyAvailable) {
                    server.CheckForMessages();
                    List<string> message = server.GetMessage();
                    if (message != null) {
                        foreach (string msg in message) {
                            Console.WriteLine("Client:  " + msg);
                        }//end for
                    }
                }//end if

                if (Console.KeyAvailable) {
                    ConsoleKeyInfo keyInfo = Console.ReadKey(true);
                    if (keyInfo.Key == ConsoleKey.I) {
                        //message to send is going to be generated here
                        Console.Write(">>  ");
                        string input = Console.ReadLine();
                        if (input != "") {
                            server.WriteMessage(input + "\n");
                        }
                    }
                    else if (keyInfo.Key == ConsoleKey.Q) {
                        server.Disconnect();
                        break;
                    }

                }
            }//end while
        }//end ServerChat
    }
}

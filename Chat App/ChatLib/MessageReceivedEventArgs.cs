using System;

namespace ChatLib {
    public class MessageReceivedEventArgs : EventArgs {

        public string message { get; set; } //read only property by removing the set;

        /// <summary>
        /// Event firing when a message is sent, holds the message value
        /// </summary>
        /// <param name="msg"></param>
        public MessageReceivedEventArgs (string msg) {
            message = msg;
        }


    }
}

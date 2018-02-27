using System;

namespace ChatLib {
    public class ChangedConnectionStatusEventArgs : EventArgs {

        public bool Connected { get; set; }

        /// <summary>
        /// Event firing when the connection state changes, holds the connection state value
        /// </summary>
        /// <param name="con"></param>
        public ChangedConnectionStatusEventArgs (bool con) {
            Connected = con;
        }


    }
}

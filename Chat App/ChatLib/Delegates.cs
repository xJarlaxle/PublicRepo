﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ChatLib {
    
        public delegate void MessageReceivedEventHandler(object sender, MessageReceivedEventArgs e);
        public delegate void ChangedConnectionStatusEventHandler(object sender, ChangedConnectionStatusEventArgs e);
        
  
}

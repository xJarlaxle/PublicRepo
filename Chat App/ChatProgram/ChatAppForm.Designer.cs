namespace ChatProgram {
    partial class ChatAppForm {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing) {
            if (disposing && (components != null)) {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent() {
            this.menuStrip1 = new System.Windows.Forms.MenuStrip();
            this.gameToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.exitToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.networkToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.connectToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.disconnectToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.panel1 = new System.Windows.Forms.Panel();
            this.gameGoesHere = new System.Windows.Forms.Label();
            this.sendMsgGroupBox = new System.Windows.Forms.GroupBox();
            this.sendMessageTxtBx = new System.Windows.Forms.TextBox();
            this.sendBtn = new System.Windows.Forms.Button();
            this.conversationBox = new System.Windows.Forms.GroupBox();
            this.conversationTxtBox = new System.Windows.Forms.TextBox();
            this.menuStrip1.SuspendLayout();
            this.panel1.SuspendLayout();
            this.sendMsgGroupBox.SuspendLayout();
            this.conversationBox.SuspendLayout();
            this.SuspendLayout();
            // 
            // menuStrip1
            // 
            this.menuStrip1.ImageScalingSize = new System.Drawing.Size(20, 20);
            this.menuStrip1.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.gameToolStripMenuItem,
            this.networkToolStripMenuItem});
            this.menuStrip1.Location = new System.Drawing.Point(0, 0);
            this.menuStrip1.Name = "menuStrip1";
            this.menuStrip1.Padding = new System.Windows.Forms.Padding(4, 2, 0, 2);
            this.menuStrip1.Size = new System.Drawing.Size(922, 24);
            this.menuStrip1.TabIndex = 0;
            this.menuStrip1.Text = "menuStrip1";
            // 
            // gameToolStripMenuItem
            // 
            this.gameToolStripMenuItem.DropDownItems.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.exitToolStripMenuItem});
            this.gameToolStripMenuItem.Name = "gameToolStripMenuItem";
            this.gameToolStripMenuItem.Size = new System.Drawing.Size(50, 20);
            this.gameToolStripMenuItem.Text = "Game";
            // 
            // exitToolStripMenuItem
            // 
            this.exitToolStripMenuItem.Name = "exitToolStripMenuItem";
            this.exitToolStripMenuItem.Size = new System.Drawing.Size(92, 22);
            this.exitToolStripMenuItem.Text = "Exit";
            this.exitToolStripMenuItem.Click += new System.EventHandler(this.exitToolStripMenuItem_Click);
            // 
            // networkToolStripMenuItem
            // 
            this.networkToolStripMenuItem.DropDownItems.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.connectToolStripMenuItem,
            this.disconnectToolStripMenuItem});
            this.networkToolStripMenuItem.Name = "networkToolStripMenuItem";
            this.networkToolStripMenuItem.Size = new System.Drawing.Size(64, 20);
            this.networkToolStripMenuItem.Text = "Network";
            // 
            // connectToolStripMenuItem
            // 
            this.connectToolStripMenuItem.Name = "connectToolStripMenuItem";
            this.connectToolStripMenuItem.Size = new System.Drawing.Size(133, 22);
            this.connectToolStripMenuItem.Text = "Connect";
            this.connectToolStripMenuItem.Click += new System.EventHandler(this.connectToolStripMenuItem_Click);
            // 
            // disconnectToolStripMenuItem
            // 
            this.disconnectToolStripMenuItem.Name = "disconnectToolStripMenuItem";
            this.disconnectToolStripMenuItem.Size = new System.Drawing.Size(133, 22);
            this.disconnectToolStripMenuItem.Text = "Disconnect";
            this.disconnectToolStripMenuItem.Click += new System.EventHandler(this.disconnectToolStripMenuItem_Click);
            // 
            // panel1
            // 
            this.panel1.BackColor = System.Drawing.SystemColors.ActiveCaptionText;
            this.panel1.Controls.Add(this.gameGoesHere);
            this.panel1.Location = new System.Drawing.Point(7, 27);
            this.panel1.Margin = new System.Windows.Forms.Padding(2);
            this.panel1.Name = "panel1";
            this.panel1.Size = new System.Drawing.Size(908, 268);
            this.panel1.TabIndex = 1;
            // 
            // gameGoesHere
            // 
            this.gameGoesHere.AutoSize = true;
            this.gameGoesHere.Font = new System.Drawing.Font("Microsoft Sans Serif", 19.8F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.gameGoesHere.ForeColor = System.Drawing.SystemColors.ControlLightLight;
            this.gameGoesHere.Location = new System.Drawing.Point(321, 111);
            this.gameGoesHere.Margin = new System.Windows.Forms.Padding(2, 0, 2, 0);
            this.gameGoesHere.Name = "gameGoesHere";
            this.gameGoesHere.Size = new System.Drawing.Size(225, 31);
            this.gameGoesHere.TabIndex = 0;
            this.gameGoesHere.Text = "Game Goes Here";
            this.gameGoesHere.TextAlign = System.Drawing.ContentAlignment.TopCenter;
            // 
            // sendMsgGroupBox
            // 
            this.sendMsgGroupBox.Controls.Add(this.sendMessageTxtBx);
            this.sendMsgGroupBox.Controls.Add(this.sendBtn);
            this.sendMsgGroupBox.Location = new System.Drawing.Point(8, 299);
            this.sendMsgGroupBox.Margin = new System.Windows.Forms.Padding(2);
            this.sendMsgGroupBox.Name = "sendMsgGroupBox";
            this.sendMsgGroupBox.Padding = new System.Windows.Forms.Padding(2);
            this.sendMsgGroupBox.Size = new System.Drawing.Size(908, 48);
            this.sendMsgGroupBox.TabIndex = 2;
            this.sendMsgGroupBox.TabStop = false;
            this.sendMsgGroupBox.Text = "Send Messages Here:";
            // 
            // sendMessageTxtBx
            // 
            this.sendMessageTxtBx.Location = new System.Drawing.Point(3, 19);
            this.sendMessageTxtBx.Margin = new System.Windows.Forms.Padding(2);
            this.sendMessageTxtBx.Name = "sendMessageTxtBx";
            this.sendMessageTxtBx.Size = new System.Drawing.Size(826, 20);
            this.sendMessageTxtBx.TabIndex = 2;
            // 
            // sendBtn
            // 
            this.sendBtn.Location = new System.Drawing.Point(832, 18);
            this.sendBtn.Margin = new System.Windows.Forms.Padding(2);
            this.sendBtn.Name = "sendBtn";
            this.sendBtn.Size = new System.Drawing.Size(73, 20);
            this.sendBtn.TabIndex = 1;
            this.sendBtn.Text = "Send";
            this.sendBtn.UseVisualStyleBackColor = true;
            this.sendBtn.Click += new System.EventHandler(this.sendBtn_Click);
            // 
            // conversationBox
            // 
            this.conversationBox.Controls.Add(this.conversationTxtBox);
            this.conversationBox.Location = new System.Drawing.Point(8, 349);
            this.conversationBox.Margin = new System.Windows.Forms.Padding(2);
            this.conversationBox.Name = "conversationBox";
            this.conversationBox.Padding = new System.Windows.Forms.Padding(2);
            this.conversationBox.Size = new System.Drawing.Size(908, 271);
            this.conversationBox.TabIndex = 3;
            this.conversationBox.TabStop = false;
            this.conversationBox.Text = "Conversation:";
            // 
            // conversationTxtBox
            // 
            this.conversationTxtBox.Location = new System.Drawing.Point(4, 17);
            this.conversationTxtBox.Margin = new System.Windows.Forms.Padding(2);
            this.conversationTxtBox.Multiline = true;
            this.conversationTxtBox.Name = "conversationTxtBox";
            this.conversationTxtBox.Size = new System.Drawing.Size(901, 249);
            this.conversationTxtBox.TabIndex = 0;
            // 
            // ChatAppForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(922, 621);
            this.Controls.Add(this.conversationBox);
            this.Controls.Add(this.sendMsgGroupBox);
            this.Controls.Add(this.panel1);
            this.Controls.Add(this.menuStrip1);
            this.MainMenuStrip = this.menuStrip1;
            this.Margin = new System.Windows.Forms.Padding(2);
            this.Name = "ChatAppForm";
            this.Text = "Chat App GUI";
            this.FormClosing += new System.Windows.Forms.FormClosingEventHandler(this.ChatAppForm_FormClosing);
            this.Load += new System.EventHandler(this.ChatAppForm_Load);
            this.menuStrip1.ResumeLayout(false);
            this.menuStrip1.PerformLayout();
            this.panel1.ResumeLayout(false);
            this.panel1.PerformLayout();
            this.sendMsgGroupBox.ResumeLayout(false);
            this.sendMsgGroupBox.PerformLayout();
            this.conversationBox.ResumeLayout(false);
            this.conversationBox.PerformLayout();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.MenuStrip menuStrip1;
        private System.Windows.Forms.ToolStripMenuItem gameToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem exitToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem networkToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem connectToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem disconnectToolStripMenuItem;
        private System.Windows.Forms.Panel panel1;
        private System.Windows.Forms.Label gameGoesHere;
        private System.Windows.Forms.GroupBox sendMsgGroupBox;
        private System.Windows.Forms.TextBox sendMessageTxtBx;
        private System.Windows.Forms.Button sendBtn;
        private System.Windows.Forms.GroupBox conversationBox;
        private System.Windows.Forms.TextBox conversationTxtBox;
    }
}


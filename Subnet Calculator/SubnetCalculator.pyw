import sys
import csv
from PyQt5.QtWidgets import QApplication, QMainWindow, QMessageBox, QFileDialog

#ADD IMPORT STATEMENT FOR YOUR GENERATED UI.PY FILE HERE
import SubnetCalculator_LIB

#CHANGE THE SECOND PARAMETER HERE TO MATCH YOUR GENERATED UI.PY FILE
class MyForm(QMainWindow, SubnetCalculator_LIB.Ui_MainWindow):

        # DO NOT MODIFY THIS CODE
    def __init__(self, parent=None):
        super(MyForm, self).__init__(parent)
        self.setupUi(self)
        # END DO NOT MODIFY

        # ADD SLOTS HERE
        self.pushButtonCalculate.clicked.connect(self.Calculate)
        self.actionOpen.triggered.connect(self.OpenSelected)
        self.actionClear.triggered.connect(self.ClearSelected)
        self.actionCalculate.triggered.connect(self.Calculate)
        self.actionExit.triggered.connect(self.ExitSelected)
        self.actionSave.triggered.connect(self.SaveAsSelected)
        self.actionDiplayInfo.triggered.connect(self.DisplayInfoSelected)

    # ADD SLOT FUNCTIONS HERE
    #Function that first clears the programm then proceeds to open a file and read its contents the passes the list to ListData
    def OpenSelected(self):
        self.ClearSelected()
        filepath = str(QFileDialog.getOpenFileName())
        filepath = filepath.split("'")[1]
        if filepath == "":
            pass
        else:
            with open(filepath, "r") as myFile:
                data = csv.reader(myFile)
                fileData = []
                for line in data:
                    fileData.append(line)
            self.ListData(fileData)

    #Simple function that clears all lines of text in the program
    def ClearSelected(self):
        self.networkAddress.clear()
        self.hostsPerSub.clear()
        self.subSize.clear()
        self.decimalOctects.clear()
        self.decimalAddress.clear()
        self.binaryOctets.clear()
        self.hexOctets.clear()
        self.networkClass.clear()
        self.numIPAddresses.clear()
        self.subnetMask.clear()
        self.binaryMask.clear()
        self.numSubnets.clear()
        self.listWidget.clear()

    #Function that handles the main error check
    def Calculate(self):
        global subInput
        subInput = self.networkAddress.toPlainText()
        count = int(subInput.count("."))
        #Try/Except block to make sure the network address is entered in correctly then sends it off to CheckHosts
        if count == 3:
            try:
                octetOne = int(subInput.split(".")[0])
                octetTwo = int(subInput.split(".")[1])
                octetThree = int(subInput.split(".")[2])
                octetFour = int(subInput.split(".")[3])
                if octetOne > 0 and octetOne <= 223 and octetTwo >= 0 and octetTwo <= 255 and octetThree >= 0 \
                and octetThree <= 255 and octetFour>= 0 and octetFour == 0:
                    self.CheckHosts()
                else:
                    QMessageBox.information(self,"Error","Network Addresses must be a valid A,B, or C class IPv4 Address")
            except ValueError:
                QMessageBox.information(self,"Error","Network Addresses must be a valid A,B, or C class IPv4 Address")
        else:
            QMessageBox.information(self,"Error","Network Addresses must be a valid A,B, or C class IPv4 Address")

    #This function handles the main actions, checks the hosts and error checks them
    def CheckHosts(self):
        global hostIpnut
        hostInput = self.hostsPerSub.toPlainText()
        hostLen = int(len(hostInput))
        x = subInput[0:subInput.index(".")]
        if int(subInput[0:subInput.index(".")]) <= 126 and int(subInput[0:subInput.index(".")])  > 0:
            global classCheck
            classCheck = "A"
        elif int(subInput[0:subInput.index(".")])  >= 128 and int(subInput[0:subInput.index(".")])  <= 191:

            classCheck = "B"
        elif int(subInput[0:subInput.index(".")])  >= 192 and int(subInput[0:subInput.index(".")]) <= 223:
            classCheck = "C"
        #As long as a proper number of hosts exists. It error checks and passes off the data to all the calculating functions
        #Gets the return from said functions and sets the Text on the main window to reflect appropriately
        if hostLen > 0:
            try:
                intValue = int(hostInput)
                if classCheck == "C" and intValue > 0 and intValue <=254:
                    self.networkClass.setText(self.CalculateNetworkClass(subInput))
                    self.decimalAddress.setText(self.CalculateDecimalAddress(subInput))
                    self.binaryOctets.setText(self.CalculateBinaryAddress(subInput))
                    self.hexOctets.setText(self.CalculateHexAddress(subInput))
                    self.numIPAddresses.setText(self.CalculateNumberOfIPAddresses(subInput))
                    self.CalculateSubnetMask(int(self.hostsPerSub.toPlainText()))
                    self.binaryMask.setText(self.CalculateBinaryMask(self.subnetMask.toPlainText()))
                    self.CalculateSubnetList(subInput, hostInput)
                elif classCheck == "B" and intValue > 0 and intValue <= 65534:
                    self.networkClass.setText(self.CalculateNetworkClass(subInput))
                    self.decimalAddress.setText(self.CalculateDecimalAddress(subInput))
                    self.binaryOctets.setText(self.CalculateBinaryAddress(subInput))
                    self.hexOctets.setText(self.CalculateHexAddress(subInput))
                    self.numIPAddresses.setText(self.CalculateNumberOfIPAddresses(subInput))
                    self.CalculateSubnetMask(int(self.hostsPerSub.toPlainText()))
                    self.binaryMask.setText(self.CalculateBinaryMask(self.subnetMask.toPlainText()))
                    self.CalculateSubnetList(subInput, hostInput)
                elif classCheck == "A" and intValue > 0 and intValue <= 16777214:
                    self.networkClass.setText(self.CalculateNetworkClass(subInput))
                    self.decimalAddress.setText(self.CalculateDecimalAddress(subInput))
                    self.binaryOctets.setText(self.CalculateBinaryAddress(subInput))
                    self.hexOctets.setText(self.CalculateHexAddress(subInput))
                    self.numIPAddresses.setText(self.CalculateNumberOfIPAddresses(subInput))
                    self.CalculateSubnetMask(int(self.hostsPerSub.toPlainText()))
                    self.binaryMask.setText(self.CalculateBinaryMask(self.subnetMask.toPlainText()))
                    self.CalculateSubnetList(subInput, hostInput)
                else:
                    QMessageBox.information(self,"Error","Please enter a valid number of hosts per subnet")
            except ValueError:
                QMessageBox.information(self,"Error","Network Addresses must be a valid A,B, or C class IPv4 Address")
        else:
            QMessageBox.information(self,"Error","Network Addresses must be a valid A,B, or C class IPv4 Address")

    #This function determines the network class based on the ip address that was handed to it, then returns it
    def CalculateNetworkClass(self, ip):
        geoffsRule = ip
        if geoffsRule[0:geoffsRule.index(".")] <= "126" and geoffsRule[0:geoffsRule.index(".")] > "0":
            x = "A"
            return x
        elif geoffsRule[0:geoffsRule.index(".")] >= "128" and geoffsRule[0:geoffsRule.index(".")] <= "191":
            x = "B"
            return x
        elif geoffsRule[0:geoffsRule.index(".")] >= "192" and geoffsRule[0:geoffsRule.index(".")]<= "223":
            x = "C"
            return x

    #Function calculates the decimal address based on the ip address that was handed to it, then returns it
    def CalculateDecimalAddress(self, ip):
        geoffsRule = ip
        self.decimalOctects.setText(subInput)
        first = int(geoffsRule.split(".")[0]) * (256**3)
        second = int(geoffsRule.split(".")[1]) * (256**2)
        third = int(geoffsRule.split(".")[2]) * 256
        fourth = int(geoffsRule.split(".")[3])
        total = first + second + third + fourth
        x = "{0}".format(total)
        return x

    #Functions that calculates the binary address based on the ip address that was handed to it, then returns it
    def CalculateBinaryAddress(self, ip):
        octetOne = int(ip.split(".")[0])
        octetTwo = int(ip.split(".")[1])
        octetThree = int(ip.split(".")[2])
        octetFour = int(ip.split(".")[3])
        binaryOctetOne = ["0","0","0","0","0","0","0","0"]
        binaryOctetTwo = ["0","0","0","0","0","0","0","0"]
        binaryOctetThree = ["0","0","0","0","0","0","0","0"]
        binaryOctetFour = ["0","0","0","0","0","0","0","0"]
        #Calculates the binary per octet
        exponent = 7
        for bit in range(len(binaryOctetOne)):
            if (2 ** exponent) <= octetOne:
                binaryOctetOne[int(bit)] = "1"
                octetOne -= (2 ** exponent)
            exponent -= 1

        exponent = 7
        for bit in range(len(binaryOctetTwo)):
            if (2 ** exponent) <= octetTwo:
                binaryOctetTwo[int(bit)] = "1"
                octetTwo -= (2 ** exponent)
            exponent -= 1

        exponent = 7
        for bit in range(len(binaryOctetThree)):
            if (2 ** exponent) <= octetThree:
                binaryOctetThree[int(bit)] = "1"
                octetThree -= (2 ** exponent)
            exponent -= 1

        exponent = 7
        for bit in range(len(binaryOctetFour)):
            if (2 ** exponent) <= octetFour:
                binaryOctetFour[int(bit)] = "1"
                octetFour -= (2 ** exponent)
            exponent -= 1

        #Returns the result of the combined binary per octet in to one string
        binary = "{0}.{1}.{2}.{3}".format("".join(binaryOctetOne),"".join(binaryOctetTwo),"".join(binaryOctetThree),"".join(binaryOctetFour))
        return binary

    #This function determines the hex address based on the ip address that was handed to it, then returns it
    def CalculateHexAddress(self, ip):
        octetOne = ip.split(".")[0]
        octetTwo = ip.split(".")[1]
        octetThree = ip.split(".")[2]
        octetFour = ip.split(".")[3]
        hex = "{0:02X}.{1:02X}.{2:02X}.{3:02X}".format(int(octetOne),int(octetTwo),int(octetThree),int(octetFour))
        return hex

    #Function determines the number of ip addresses based on the ip address that was handed to it, then returns it
    def CalculateNumberOfIPAddresses(self, ip):
        numIPs = ip.split(".")[0]
        if int(numIPs) > 0 and int(numIPs) <= 126:
            x = "16,777,216"

        elif int(numIPs) >= 128 and int(numIPs) <= 191:
            x = "65,536"
        else:
            x = "256"
        return x

    #This function calculates the subnet mask based on the number of hosts
    def CalculateSubnetMask(self, hosts):
        numberOfHosts = int(hosts)
        #starts by laying out al 32 potential bits in the 4 octets
        allOctets = ["0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0"]
        exponent = 1
        bitSpot = 1
        ipIncrement = 0
        #runs through the list from end to beginning and marks the spot where the hosts fits
        for bit in allOctets[31:0:-1]:
            ipIncrement = (2 ** exponent)
            if (ipIncrement - 2) < numberOfHosts:
                exponent += 1
                bitSpot += 1

        octetNumber = 0
        if bitSpot > 8 and bitSpot <= 16:
            bitSpot = bitSpot - 8
            octetNumber += 3

        elif bitSpot > 16 and bitSpot <= 24:
            bitSpot = bitSpot - 16
            octetNumber += 2
        else:
            octetNumber += 4

        if octetNumber == 4:
            setValue = ("255.255.255.{0}".format(256 - (2 ** bitSpot)))
        elif octetNumber == 3:
            setValue = ("255.255.{0}.0".format(256 - (2 ** bitSpot)))
        elif octetNumber == 2:
            setValue = ("255.{0}.0.0".format(256 - (2 ** bitSpot)))
        global cidrCount
        cidrCount = ((octetNumber * 8) - bitSpot)
        self.subnetMask.setText("{0} or /{1}".format(setValue, cidrCount))
        self.subSize.setText("{0}".format(ipIncrement))


    #This function converts the subnet mask in to binary
    def CalculateBinaryMask(self, binaryMask):
        #receives the subnet mask in decimal and splits it up
        binaryMask = binaryMask.replace("."," ")
        octetOne = int(binaryMask.split(" ")[0])
        octetTwo = int(binaryMask.split(" ")[1])
        octetThree = int(binaryMask.split(" ")[2])
        octetFour = int(binaryMask.split(" ")[3])
        binaryOctetOne = ["0","0","0","0","0","0","0","0"]
        binaryOctetTwo = ["0","0","0","0","0","0","0","0"]
        binaryOctetThree = ["0","0","0","0","0","0","0","0"]
        binaryOctetFour = ["0","0","0","0","0","0","0","0"]
        exponent = 7
        #then converts each octet to binary
        for bit in range(len(binaryOctetOne)):
            if (2 ** exponent) <= octetOne:
                binaryOctetOne[int(bit)] = "1"
                octetOne -= (2 ** exponent)
            exponent -= 1

        exponent = 7
        for bit in range(len(binaryOctetTwo)):
            if (2 ** exponent) <= octetTwo:
                binaryOctetTwo[int(bit)] = "1"
                octetTwo -= (2 ** exponent)
            exponent -= 1

        exponent = 7
        for bit in range(len(binaryOctetThree)):
            if (2 ** exponent) <= octetThree:
                binaryOctetThree[int(bit)] = "1"
                octetThree -= (2 ** exponent)
            exponent -= 1

        exponent = 7
        for bit in range(len(binaryOctetFour)):
            if (2 ** exponent) <= octetFour:
                binaryOctetFour[int(bit)] = "1"
                octetFour -= (2 ** exponent)
            exponent -= 1

        #combines all octets that have been converted to one string and determines the CIDR
        binary = "{0}.{1}.{2}.{3}".format("".join(binaryOctetOne),"".join(binaryOctetTwo),"".join(binaryOctetThree),"".join(binaryOctetFour))
        global cidrCount
        cidrCount = binary.count("1")
        return binary

    #This is the big one, the one that calculates the IP ranges based on the ip address that was handed to it as well as the hosts
    def CalculateSubnetList(self, netIP, hosts):
        ip = subInput
        numberOfHosts = int(hosts)
        ipClass = self.networkClass.toPlainText()
        global saveToFileList
        saveToFileList = []
        self.listWidget.clear()
        allOctets = ["0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","1"]
        exponent = 1
        ipIncrement = 0
        #starts out with a list of all 32 bits and determines the increment in which the ip range will increase by
        for bit in allOctets[31:0:-1]:
            ipIncrement = (2 ** exponent)
            if (ipIncrement - 2) < numberOfHosts:
                exponent += 1
        #creates lists for each octet to determine how much each octet will increase by for class b and a calculations
        fO = -1
        tO = -1
        sO = -1
        count = 0
        fourthOctetList = []
        thirdOctetList = []
        secondOctetList = []
        while count < (ipIncrement):
            fO += 1
            count += 1
            if fO == 255:
                fourthOctetList.append(fO)
                fO = 0
                tO += 1
                thirdOctetList.append(tO)
                if tO == 255:
                    tO = 0
                    sO += 1
        if fourthOctetList == []:
            fourthOctetList.append(fO)
        if thirdOctetList == []:
            thirdOctetList.append(tO)
        if secondOctetList == []:
            secondOctetList.append(sO)

        #the main calculations for ip range, based on how many hosts and class it calculates the ranges
        first = ip.split(".")[0]
        second = ip.split(".")[1]
        third = ip.split(".")[2]
        increment = (max(fourthOctetList) + 1)
        listTwoMax = (max(thirdOctetList))
        if listTwoMax < 0:
            listTwoMax = 0
        incrementTwo = (listTwoMax + 1)
        listThreeMax = max(secondOctetList)
        if listThreeMax < 0:
            listThreeMax = 0
        incrementThree = (listThreeMax + 1)
        if ipClass == "C":
            fourthOctet = 0
            while fourthOctet < 255:
                beginning = fourthOctet
                ending = (fourthOctet + increment) - 1
                self.listWidget.addItem("{0}.{1}.{2}.{3} - {4}.{5}.{6}.{7}".format(first,second,third,beginning,first,second,third,ending))
                saveToFileList.append("{0}.{1}.{2}.{3} - {4}.{5}.{6}.{7}".format(first,second,third,beginning,first,second,third,ending))
                beginning += 1
                fourthOctet += increment
        elif ipClass == "B" and numberOfHosts >= 255:
            fourOctet = 0
            threeOctet = 0
            threeOctetEnd = -1
            threeOctetbeg = (0 - incrementTwo)
            while threeOctetEnd < 255:
                beginningTwo = fourOctet
                endingTwo = (fourOctet + increment) - 1
                fourOctet += increment
                if fourOctet >= 255:
                    threeOctetEnd += (incrementTwo)
                    threeOctetbeg += incrementTwo
                    self.listWidget.addItem("{0}.{1}.{2}.{3} - {4}.{5}.{6}.{7}".format(first,second,threeOctetbeg,beginningTwo,first,second,threeOctetEnd,endingTwo))
                    saveToFileList.append("{0}.{1}.{2}.{3} - {4}.{5}.{6}.{7}".format(first,second,threeOctetbeg,beginningTwo,first,second,threeOctetEnd,endingTwo))
                    beginningTwo += 1
                    fourOctet = 0
        elif ipClass == "B" and numberOfHosts < 255:
            fourthOctet = 0
            thirdOctet = 0
            thirdOctetEnd = -1
            thirdOctetbeg = (0 - incrementTwo)
            beginning = 0
            ending = 0
            while thirdOctet < 255:
                while fourthOctet < 255:
                    beginning = fourthOctet
                    ending = (fourthOctet + increment) - 1
                    self.listWidget.addItem("{0}.{1}.{2}.{3} - {4}.{5}.{6}.{7}".format(first,second,thirdOctet,beginning,first,second,thirdOctet,ending))
                    saveToFileList.append("{0}.{1}.{2}.{3} - {4}.{5}.{6}.{7}".format(first,second,thirdOctet,beginning,first,second,thirdOctet,ending))
                    beginning += 1
                    fourthOctet += increment
                    if fourthOctet >= 255:
                        thirdOctet += incrementTwo
                        if thirdOctet <= 255:
                            fourthOctet = 0
                        else:
                            fourthOctet = 255
        elif ipClass == "A" and numberOfHosts >= 255 and numberOfHosts <= 65534:
            fourOctet = 0
            threeOctet = 0
            threeOctetEnd = -1
            threeOctetbeg = (0 - incrementTwo)
            secondOctetEnd = 0
            secondOctetbeg = ((0 - incrementThree) + 1)
            while secondOctetEnd <= 255:
                beginningTwo = fourOctet
                endingTwo = (fourOctet + increment) - 1
                fourOctet += increment
                if fourOctet >= 255:
                    threeOctetEnd += (incrementTwo)
                    threeOctetbeg += incrementTwo
                    self.listWidget.addItem("{0}.{1}.{2}.{3} - {4}.{5}.{6}.{7}".format(first,secondOctetbeg,threeOctetbeg,beginningTwo,first,secondOctetEnd,threeOctetEnd,endingTwo))
                    saveToFileList.append("{0}.{1}.{2}.{3} - {4}.{5}.{6}.{7}".format(first,secondOctetbeg,threeOctetbeg,beginningTwo,first,secondOctetEnd,threeOctetEnd,endingTwo))
                    beginningTwo += 1
                    fourOctet = 0
                    if threeOctetEnd >= 255:
                        secondOctetEnd += (incrementThree)
                        secondOctetbeg += incrementThree
                        threeOctetEnd = -1
                        threeOctetbeg = (0 - incrementTwo)
                        fourOctet = 0
        elif ipClass == "A" and numberOfHosts > 65534 and numberOfHosts <= 130070:
            fourOctet = 0
            threeOctet = 0
            threeOctetEnd = -1
            threeOctetbeg = (0 - incrementTwo)
            secondOctetEnd = 0
            secondOctetbeg = -1
            while secondOctetEnd < 255:
                beginningTwo = fourOctet
                endingTwo = (fourOctet + increment) - 1
                fourOctet += increment
                if fourOctet >= 255:
                    threeOctetEnd += (incrementTwo)
                    threeOctetbeg += incrementTwo
                    fourOctet = 0
                    if threeOctetEnd >= 255:
                        secondOctetEnd += (incrementThree) - 1
                        secondOctetbeg += incrementThree - 1
                        self.listWidget.addItem("{0}.{1}.{2}.{3} - {4}.{5}.{6}.{7}".format(first,secondOctetbeg,threeOctetbeg,beginningTwo,first,secondOctetEnd,threeOctetEnd,endingTwo))
                        saveToFileList.append("{0}.{1}.{2}.{3} - {4}.{5}.{6}.{7}".format(first,secondOctetbeg,threeOctetbeg,beginningTwo,first,secondOctetEnd,threeOctetEnd,endingTwo))
                        beginningTwo += 1
                        threeOctetEnd = -1
                        threeOctetbeg = (0 - incrementTwo)
                        fourOctet = 0
                        secondOctetbeg += 1
                        secondOctetEnd += 1
        elif ipClass == "A" and numberOfHosts > 130070 and numberOfHosts <= 4194302:
            fourOctet = 0
            threeOctet = 0
            threeOctetEnd = -1
            threeOctetbeg = (0 - incrementTwo)
            secondOctetEnd = 0
            secondOctetbeg = ((incrementThree) - 1) * -1
            while secondOctetEnd < 255:
                beginningTwo = fourOctet
                endingTwo = (fourOctet + increment) - 1
                fourOctet += increment
                if fourOctet >= 255:
                    threeOctetEnd += (incrementTwo)
                    threeOctetbeg += incrementTwo
                    fourOctet = 0
                    if threeOctetEnd >= 255:
                        secondOctetEnd += (incrementThree) - 1
                        secondOctetbeg += incrementThree - 1
                        self.listWidget.addItem("{0}.{1}.{2}.{3} - {4}.{5}.{6}.{7}".format(first,secondOctetbeg,threeOctetbeg,beginningTwo,first,secondOctetEnd,threeOctetEnd,endingTwo))
                        saveToFileList.append("{0}.{1}.{2}.{3} - {4}.{5}.{6}.{7}".format(first,secondOctetbeg,threeOctetbeg,beginningTwo,first,secondOctetEnd,threeOctetEnd,endingTwo))
                        beginningTwo += 1
                        threeOctetEnd = -1
                        threeOctetbeg = (0 - incrementTwo)
                        fourOctet = 0
                        secondOctetbeg += 1
                        secondOctetEnd += 1
        elif ipClass == "A" and numberOfHosts > 4194302 and numberOfHosts <= 8388606:
            fourOctet = 0
            threeOctet = 0
            threeOctetEnd = -1
            threeOctetbeg = (0 - incrementTwo)
            secondOctetEnd = -1
            secondOctetbeg = ((incrementThree)) * -1
            while secondOctetEnd < 255:
                beginningTwo = fourOctet
                endingTwo = (fourOctet + increment) - 1
                fourOctet += increment
                if fourOctet >= 255:
                    threeOctetEnd += (incrementTwo)
                    threeOctetbeg += incrementTwo
                    fourOctet = 0
                    if threeOctetEnd >= 255:
                        secondOctetEnd += (incrementThree) - 1
                        secondOctetbeg += incrementThree
                        self.listWidget.addItem("{0}.{1}.{2}.{3} - {4}.{5}.{6}.{7}".format(first,secondOctetbeg,threeOctetbeg,beginningTwo,first,secondOctetEnd,threeOctetEnd,endingTwo))
                        saveToFileList.append("{0}.{1}.{2}.{3} - {4}.{5}.{6}.{7}".format(first,secondOctetbeg,threeOctetbeg,beginningTwo,first,secondOctetEnd,threeOctetEnd,endingTwo))
                        beginningTwo += 1
                        threeOctetEnd = -1
                        threeOctetbeg = (0 - incrementTwo)
                        fourOctet = 0
                        secondOctetbeg -= 1

        elif ipClass == "A" and numberOfHosts > 8388606:
            fourOctet = 0
            threeOctet = 0
            threeOctetEnd = -1
            threeOctetbeg = (0 - incrementTwo)
            secondOctetEnd = -1
            secondOctetbeg = ((incrementThree)) * -1
            while secondOctetEnd < 255:
                beginningTwo = fourOctet
                endingTwo = (fourOctet + increment) - 1
                fourOctet += increment
                if fourOctet >= 255:
                    threeOctetEnd += (incrementTwo)
                    threeOctetbeg += incrementTwo
                    fourOctet = 0
                    if threeOctetEnd >= 255:
                        secondOctetEnd += (incrementThree) - 2
                        secondOctetbeg += incrementThree
                        self.listWidget.addItem("{0}.{1}.{2}.{3} - {4}.{5}.{6}.{7}".format(first,secondOctetbeg,threeOctetbeg,beginningTwo,first,secondOctetEnd,threeOctetEnd,endingTwo))
                        saveToFileList.append("{0}.{1}.{2}.{3} - {4}.{5}.{6}.{7}".format(first,secondOctetbeg,threeOctetbeg,beginningTwo,first,secondOctetEnd,threeOctetEnd,endingTwo))
                        beginningTwo += 1
                        threeOctetEnd = -1
                        threeOctetbeg = (0 - incrementTwo)
                        fourOctet = 0
                        secondOctetbeg -= 1
                        secondOctetEnd += 1
        elif ipClass == "A" and numberOfHosts < 255 and numberOfHosts >= 127:
            fourOctet = 0
            threeOctet = 0
            threeOctetEnd = -1
            threeOctetbeg = (0 - incrementTwo)
            secondOctetEnd = 0
            secondOctetbeg = ((0 - incrementThree) + 1)
            while secondOctetEnd <= 255:
                beginningTwo = fourOctet
                endingTwo = (fourOctet + increment) - 1
                fourOctet += increment
                if fourOctet >= 255:
                    threeOctetEnd += (incrementTwo)
                    threeOctetbeg += incrementTwo
                    self.listWidget.addItem("{0}.{1}.{2}.{3} - {4}.{5}.{6}.{7}".format(first,secondOctetbeg,threeOctetbeg,beginningTwo,first,secondOctetEnd,threeOctetEnd,endingTwo))
                    saveToFileList.append("{0}.{1}.{2}.{3} - {4}.{5}.{6}.{7}".format(first,secondOctetbeg,threeOctetbeg,beginningTwo,first,secondOctetEnd,threeOctetEnd,endingTwo))
                    beginningTwo += 1
                    fourOctet = 0
                    if threeOctetEnd >= 255:
                        secondOctetEnd += (incrementThree)
                        secondOctetbeg += incrementThree
                        threeOctetEnd = -1
                        threeOctetbeg = (0 - incrementTwo)
                        fourOctet = 0
        elif ipClass == "A" and numberOfHosts < 255:
            fourthOctet = 0
            thirdOctet = 0
            secondOctet = 0
            thirdOctetEnd = -1
            thirdOctetbeg = (0 - incrementTwo)
            beginning = 0
            ending = 0
            while secondOctet < 256:
                beginning = fourthOctet
                ending = (fourthOctet + increment) - 1
                self.listWidget.addItem("{0}.{1}.{2}.{3} - {4}.{5}.{6}.{7}".format(first,secondOctet,thirdOctet,beginning,first,secondOctet,thirdOctet,ending))
                saveToFileList.append("{0}.{1}.{2}.{3} - {4}.{5}.{6}.{7}".format(first,secondOctet,thirdOctet,beginning,first,secondOctet,thirdOctet,ending))
                beginning += 1
                fourthOctet += increment
                if fourthOctet >= 255:
                    thirdOctet += incrementTwo
                    if thirdOctet <= 255:
                        fourthOctet = 0
                    else:
                        secondOctet += 1
                        fourthOctet = 0
                        thirdOctet = 0
        numOfSubnets = self.listWidget.count()
        self.numSubnets.setText(str(numOfSubnets))

    #this function responds to the exit option being selected
    def ExitSelected(self):
        reply = QMessageBox.question(self, "Exit", "Are you sure you want to exit the program?", QMessageBox.Yes, QMessageBox.No)
        if reply == QMessageBox.Yes:
            QApplication.closeAllWindows()





    #ADD HELPER FUNCTIONS HERE

    #this function lists the data pulled ffrom a file being opened. also error checks the file to make sure it has enough lines
    def ListData(self, list):
        listData = list
        length = len(listData)
        if length < 13:
            QMessageBox.information(self, "Error", "The file you are trying to load is not compatible.  Maybe because there are not enough lines.")
        else:
            self.networkAddress.setText("".join(listData[0]))
            self.hostsPerSub.setText("".join(listData[1]))
            self.subSize.setText("".join(listData[2]))
            self.decimalOctects.setText("".join(listData[3]))
            self.decimalAddress.setText("".join(listData[4]))
            self.binaryOctets.setText("".join(listData[5]))
            self.hexOctets.setText("".join(listData[6]))
            self.networkClass.setText("".join(listData[7]))
            self.numIPAddresses.setText("".join(listData[8]))
            self.subnetMask.setText("".join(listData[9]))
            self.binaryMask.setText("".join(listData[10]))
            self.numSubnets.setText("".join(listData[11]))
            for count in listData[12:length:1]:
                self.listWidget.addItem("".join(count))

    #this function saves the data that has been calculated to a file path and name the user chooses
    def SaveAsSelected(self):
        filepath = str(QFileDialog.getSaveFileName())
        filepath = filepath.split("'")[1]
        if filepath == "":
            pass
        else:
            with open(str(filepath), "w") as myFile:
                myFile.write(self.networkAddress.toPlainText() + "\n")
                myFile.write(self.hostsPerSub.toPlainText() + "\n")
                myFile.write(self.subSize.toPlainText() + "\n")
                myFile.write(self.decimalOctects.toPlainText() + "\n")
                myFile.write(self.decimalAddress.toPlainText() + "\n")
                myFile.write(self.binaryOctets.toPlainText() + "\n")
                myFile.write(self.hexOctets.toPlainText() + "\n")
                myFile.write(self.networkClass.toPlainText() + "\n")
                myFile.write(self.numIPAddresses.toPlainText() + "\n")
                myFile.write(self.subnetMask.toPlainText() + "\n")
                myFile.write(self.binaryMask.toPlainText() + "\n")
                myFile.write(self.numSubnets.toPlainText() + "\n")
                self.Calculate()
                for line in saveToFileList:
                    myFile.write("".join(line + "\n"))

    #this function calculates the subnetsize based on the CIDR Count
    def CalculateSubnetSize(self, cidrcount):
            subs = cidrcount + 1
            return subs

    #function calculates the number of subnets based on the list widget
    def CalculateNumberOfSubnets(self):
        numberOfSubnets = str(self.listWidget.count())
        return numberOfSubnets

    def DisplayInfoSelected(self):
        QMessageBox.information(self,"Designer Information","This calculator was constructed by Brian Willett.  Last Updated: December 13, 2016")

# DO NOT MODIFY THIS CODE
if __name__ == "__main__":
    app = QApplication(sys.argv)
    the_form = MyForm()
    the_form.show()
    sys.exit(app.exec_())
# END DO NOT MODIFY

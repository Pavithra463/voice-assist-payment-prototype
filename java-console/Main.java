package voiceassist;

import java.util.Scanner;

//Handles all spoken-style feedback (text printed as [VOICE] for now)
class VoiceOutput {
 static void speak(String message) {
     System.out.println("[VOICE]: " + message);
 }
}

//Authentication step before any transaction
class Authenticator {
 private static final String PIN = "1234";

 static boolean verify(Scanner sc) {
     VoiceOutput.speak("Please enter your 4 digit PIN to confirm.");
     String entered = sc.nextLine().trim();
     return entered.equals(PIN);
 }
}

//Abstraction: common flow for every payment service
abstract class PaymentService {
 protected String name;
 protected double amount;

 PaymentService(String name) {
     this.name = name;
 }

 abstract boolean collectAndValidate(Scanner sc);
 abstract void process();

 public void execute(Scanner sc) {
     VoiceOutput.speak("You selected " + name + ".");
     if (!collectAndValidate(sc)) {
         VoiceOutput.speak("Invalid details. Transaction cancelled.");
         return;
     }
     if (!Authenticator.verify(sc)) {
         VoiceOutput.speak("Authentication failed. Transaction cancelled.");
         return;
     }
     process();
 }

 protected boolean readAmount(Scanner sc) {
     VoiceOutput.speak("Please say the amount.");
     try {
         amount = Double.parseDouble(sc.nextLine().trim());
     } catch (NumberFormatException e) {
         return false;
     }
     return amount > 0;
 }
}

class MoneyTransfer extends PaymentService {
 private String recipient;

 MoneyTransfer() { super("Money Transfer"); }

 boolean collectAndValidate(Scanner sc) {
     VoiceOutput.speak("Please say the recipient name.");
     recipient = sc.nextLine().trim();
     if (recipient.isEmpty()) return false;
     return readAmount(sc);
 }

 void process() {
     VoiceOutput.speak("Transferred " + amount + " rupees to " + recipient + ". Payment successful.");
 }
}

class BillPayment extends PaymentService {
 private String consumerNumber;

 BillPayment() { super("Electricity Bill Payment"); }

 boolean collectAndValidate(Scanner sc) {
     VoiceOutput.speak("Please say your consumer number.");
     consumerNumber = sc.nextLine().trim();
     if (!consumerNumber.matches("\\d{6,12}")) return false;
     return readAmount(sc);
 }

 void process() {
     VoiceOutput.speak("Paid " + amount + " rupees for consumer number " + consumerNumber + ". Payment successful.");
 }
}

class MobileRecharge extends PaymentService {
 private String mobile;

 MobileRecharge() { super("Mobile Recharge"); }

 boolean collectAndValidate(Scanner sc) {
     VoiceOutput.speak("Please say the 10 digit mobile number.");
     mobile = sc.nextLine().trim();
     if (!mobile.matches("\\d{10}")) return false;
     return readAmount(sc);
 }

 void process() {
     VoiceOutput.speak("Recharged " + mobile + " with " + amount + " rupees. Recharge successful.");
 }
}

class QRPayment extends PaymentService {
 private String qrData;

 QRPayment() { super("QR Payment"); }

 boolean collectAndValidate(Scanner sc) {
     VoiceOutput.speak("Please enter the QR merchant code.");
     qrData = sc.nextLine().trim();
     if (qrData.isEmpty()) return false;
     return readAmount(sc);
 }

 void process() {
     VoiceOutput.speak("Paid " + amount + " rupees to merchant " + qrData + ". Payment successful.");
 }
}

//Maps the (speech-to-text) command to the right service
class CommandMapper {
 static PaymentService map(String command) {
     String c = command.toLowerCase();
     if (c.contains("transfer") || c.contains("send")) return new MoneyTransfer();
     if (c.contains("electric") || c.contains("bill")) return new BillPayment();
     if (c.contains("recharge")) return new MobileRecharge();
     if (c.contains("qr")) return new QRPayment();
     return null;
 }
}

public class Main {
 public static void main(String[] args) {
     Scanner sc = new Scanner(System.in);
     VoiceOutput.speak("Welcome. You can say: money transfer, electricity bill, mobile recharge, or QR payment. Say exit to stop.");

     while (true) {
         System.out.print("Command (typed for now): ");
         String command = sc.nextLine().trim();
         if (command.equalsIgnoreCase("exit")) {
             VoiceOutput.speak("Thank you. Goodbye.");
             break;
         }
         PaymentService service = CommandMapper.map(command);
         if (service == null) {
             VoiceOutput.speak("Sorry, I did not understand. Please try again.");
         } else {
             service.execute(sc);
         }
     }
     sc.close();
 }
}
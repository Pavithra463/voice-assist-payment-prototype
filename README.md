# Voice Assist Payment Prototype

A prototype of a voice-guided payment application designed to make digital payments easier for visually impaired and less-educated users. Instead of reading screens and filling many fields, the user speaks a command and the app guides them step by step with voice.

> **Note:** This is a learning prototype. All payments are simulated. There is no real bank, payment gateway, or real money involved.

## What it does

The user can say or tap one of these services:

- Send Money
- Electricity Bill
- Water Bill
- Mobile Recharge
- QR Payment
- Add Money (wallet)
- Check Balance
- Transaction History

Flow for every payment:

1. The user speaks a command (for example, "mobile recharge").
2. Speech is converted to text and mapped to the matching service.
3. The app asks for the required details by voice (number, amount, and so on).
4. The details are validated (digit length, valid amount, sufficient balance).
5. The user confirms with a typed PIN.
6. The app shows a success page and reads the result aloud.

## Two parts in this repository

| Part | Technology | Purpose |
|---|---|---|
| `java-console/` | Java (OOP) | Console version showing the core design: abstract `PaymentService` class with one subclass per service, a command mapper, validation, and PIN authentication. Typed text stands in for voice. |
| `web/voice-pay.html` | HTML, CSS, JavaScript | Voice-enabled web version using the browser's Web Speech API, with multiple pages (home, service form, PIN, success, balance, history). |

## Concepts used

- **Abstraction:** `PaymentService` defines the common structure; each service hides its own details.
- **Inheritance and polymorphism:** `MoneyTransfer`, `BillPayment`, `MobileRecharge`, `QRPayment` and `AddMoney` extend the base class.
- **Input validation:** mobile and consumer numbers, amounts, and balance checks.
- **Voice input and output:** Web Speech API (`SpeechRecognition` for input, `SpeechSynthesis` for spoken replies).

## How to run

### Web version
1. Download `web/voice-pay.html`.
2. Open it in **Google Chrome** and allow microphone access.
3. Tap the microphone and say a command such as "mobile recharge", or tap a service tile, or type your answers.
4. Demo PIN: `1234`. Starting wallet balance: Rs. 10,000 (stored in the browser only).

### Java console version
1. Open the `java-console` folder in Eclipse (or any Java IDE).
2. Run `Main.java` as a Java application.
3. Type a command such as `mobile recharge` and follow the prompts. Demo PIN: `1234`.

## Limitations

- Payments are simulated; no real transactions happen.
- Speech recognition uses the browser's built-in service, so it works best in Chrome with an internet connection.
- The PIN is a fixed demo value and is typed, not spoken, so nobody nearby can hear it.
- There is no speaker verification. The app does not identify who is speaking.

## Future improvements

- Speaker verification and biometric authentication (fingerprint or face).
- A database for users and transactions, with a backend server.
- Integration with a real payment gateway.
- Support for more languages, including Tamil and Hindi.
- Better handling of background noise.

## Author

Pavithra A, Information Technology student.

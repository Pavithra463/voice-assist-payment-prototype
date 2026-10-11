# Voice Assist Payment Prototype

A prototype of a voice-guided payment application designed to make digital payments easier for visually impaired and less-educated users. Instead of reading screens and filling many fields, the user speaks a command and the app guides them step by step with voice, in **English, Hindi, or Tamil**.

> **Note:** This is a learning prototype. All payments are simulated. There is no real bank, payment gateway, or real money involved.

**Live demo:** https://pavithra463.github.io/voice-assist-payment-prototype/web/voice-pay.html
(open in Chrome and allow the microphone)

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

### Flow for every payment

1. The user picks a language (English, Hindi, or Tamil) from the dropdown.
2. The user speaks a command (for example, "mobile recharge", "मोबाइल रिचार्ज", or "மொபைல் ரீசார்ஜ்").
3. Speech is converted to text and mapped to the matching service.
4. The app asks for the required details by voice (number, amount, and so on).
5. The details are validated (digit length, valid amount, sufficient balance).
6. The user confirms with a typed PIN.
7. The app shows a success page and reads the result aloud.

## Languages supported

| Language | Voice input | Spoken replies | On-screen text |
|---|---|---|---|
| English (India) | Yes | Yes | Yes |
| Hindi (हिन्दी) | Yes | Yes, if the device has a Hindi voice | Yes |
| Tamil (தமிழ்) | Yes | Yes, if the device has a Tamil voice | Yes |

- Commands, prompts, error messages, and the PIN and success screens are available in all three languages.
- Spoken numbers are understood as digits and as common number words (for example, "five hundred", "पाँच सौ", "ஐநூறு").
- If a device has no voice for the chosen language, the app shows the reply as text instead of speaking it.

## Two parts in this repository

| Part | Technology | Purpose |
|---|---|---|
| `java-console/` | Java (OOP) | Console version showing the core design: abstract `PaymentService` class with one subclass per service, a command mapper, validation, and PIN authentication. Typed text stands in for voice. English only. |
| `web/voice-pay.html` | HTML, CSS, JavaScript | Voice-enabled web version using the browser's Web Speech API, with multiple pages (home, service form, PIN, success, balance, history) and English, Hindi, and Tamil support. |

## Concepts used

- **Abstraction:** `PaymentService` defines the common structure; each service hides its own details.
- **Inheritance and polymorphism:** `MoneyTransfer`, `BillPayment`, `MobileRecharge`, `QRPayment` and `AddMoney` extend the base class.
- **Input validation:** mobile and consumer numbers, amounts, and balance checks.
- **Voice input and output:** Web Speech API (`SpeechRecognition` for input, `SpeechSynthesis` for spoken replies).
- **Multi-language support:** a translation table for English, Hindi, and Tamil, with command keywords matched in all three languages.

## How to run

### Web version

1. Download `web/voice-pay.html`.
2. Open it in Google Chrome and allow microphone access.
3. Choose a language from the dropdown in the header.
4. Tap the microphone and say a command such as "mobile recharge", or tap a service tile, or type your answers.

Demo PIN: `1234`. Starting wallet balance: Rs. 10,000 (stored in the browser only).

### Java console version

1. Open the `java-console` folder in Eclipse (or any Java IDE).
2. Run `Main.java` as a Java application.
3. Type a command such as `mobile recharge` and follow the prompts. Demo PIN: `1234`.

## Limitations

- Payments are simulated; no real transactions happen.
- Speech recognition uses the browser's built-in service, so it works best in Chrome with an internet connection.
- Spoken replies in Hindi and Tamil depend on the voices installed on the device.
- Hindi and Tamil commands are matched by keywords, so unusual wording or spelling may not be recognized.
- The Java console version supports English only.
- The PIN is a fixed demo value and is typed, not spoken, so nobody nearby can hear it. In a real system it would be verified on a secure server.
- There is no speaker verification. The app does not identify who is speaking.

## Future improvements

- Speaker verification and biometric authentication (fingerprint or face).
- A database for users and transactions, with a backend server.
- Integration with a real payment gateway.
- More Indian languages, and a smarter intent parser for natural sentences.
- Better handling of background noise.

## Author

Pavithra A, Information Technology student.

# Apple Wallet Pass Generator

This code is a standalone app built using the Spring framework and Java 17 to generate Apple Wallet passes. I implemented this with NFC communication capabilities by emulating the VAS protocol with the MiFare DesFire NFC protocol and decided to generalize the code so that making all types of passes would be easy.

There are a few things you **should** do to make this code work:

You **will** need a Developer Account with Apple to generate the necessary signing certs to generate your private key.
You will also need to download Apple's WWDR certificate. You will need to add the private key, the signing certificate and the Apple WWDR intermediary certificate in a Java Keystore (you can do this easily through the command line using OpenSSL, remembering to set a password) and the code will be able to generate the signature to distribute your pass. For an extra layer of security, you can store your Keystore, along with any other files and private data you use, in an AWS S3 bucket - this is what I used when working on the project.

You **may** need to download Gradle, and familiarize yourself with Spring Boot.

You **will** have to go to localhost:port/download URL to download the pass.

A quick rundown of the code structure:
1. The directory **pass** contains the files you will zip - this will be the pass. Apple Wallet passes are simply .zip files renamed as .pkpass so your iOS device knows to open it - this code creates a temp directory with the files in **pass** and sends it as an HTTP response to the above URL. You **need** at least: icon.png, icon@2x.png, logo.png, logo@2x.png, manifest.json, pass.json, and signature for a valid pass
2. The directory "utils" contains the logic of the code - separate classes for encoding, signing, updating, zipping, and creating the pass
3. All other files run the Spring app so you can download the passes

Please leave any questions or doubts or reach out to me with anything.

# Readings
https://developer.apple.com/library/archive/documentation/UserExperience/Conceptual/PassKit_PG/index.html#//apple_ref/doc/uid/TP40012195-CH1-SW1
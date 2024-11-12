# Contact Manager Application

This is a **Contact Manager** application built using **Spring Boot** and **Thymeleaf** for the frontend, with **Tailwind CSS** for styling. The application allows users to save their contact information and images to the cloud. It uses **Google Authentication** for user login and integrates with **Cloudinary** to store images. Contacts are currently stored in a local SQL database.

## Features
- Add, view, and delete contacts
- Cloud storage integration with **Cloudinary** for storing images
- User authentication using **Google OAuth2**
- Contacts stored in a local SQL database
- User-friendly and responsive UI built with **Tailwind CSS**
- Built using **Spring Boot** and **Thymeleaf**

## Prerequisites

Before running the project, make sure you have the following setup:

### 1. **Google API Credentials (for Google Authentication)**
   This project uses **Google OAuth2** for user authentication. You'll need to set up your own Google Cloud project and generate OAuth2 credentials (Client ID and Client Secret).

   
### 2. **Cloudinary Account (for Image Storage)**
   This project integrates with **Cloudinary** to store images. You'll need to create a Cloudinary account and obtain your **Cloud Name**, **API Key**, and **API Secret**.

   
### 3. **SQL Database (for Contact Storage)**
   Contacts are currently stored in a local SQL database (MySQL). You need to have a MySQL database set up and configured.

   

### 4. **Maven**
   This project uses **Maven** as the build tool. Ensure Maven is installed on your machine. To verify, run:

   
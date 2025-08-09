# EcoPoints – Plastic Cover Return System

A **full-stack web application** that encourages customers to return plastic covers from previous purchases by rewarding them with EcoPoints.  
The system scans the barcode on the cover, updates the user's EcoPoints balance, and sends an email notification with the updated total.

---

## 📌 Features

- 📦 **Barcode Scanning** – Customers scan the barcode on returned plastic covers.
- ♻️ **EcoPoints Rewards** – +10 EcoPoints for every successful return.
- 📧 **Email Notifications** – Automatic email sent after each update showing the total EcoPoints.
- 🌐 **Full Stack Architecture** – React frontend and Spring Boot backend integration.
- 📊 **User Account Tracking** – Persistent eco-points balance for each user.

---

## 🛠 Tech Stack

**Frontend**
- React.js
- Axios (API communication)
- HTML5, CSS3, JavaScript

**Backend**
- Spring Boot (Java)
- REST API
- MySQL (database)
- JavaMail API (email notifications)

---

## 📂 Folder Structure

eco-points-system/
│
├── frontend/ # React application
│ ├── public/
│ ├── src/
│ ├── package.json
│ └── ...
│
├── backend/ # Spring Boot application
│ ├── src/main/java/
│ ├── src/main/resources/
│ ├── pom.xml
│ └── ...
│
└── README.md


---

## ⚙️ Setup & Installation

### 1️⃣ Clone the Repository
```bash
git clone https://github.com/your-username/eco-points-system.git
cd eco-points-system
```
### 2️⃣ Backend Setup (Spring Boot)
```bash
cd backend
# Build and run
./mvnw spring-boot:run
```

**Backend will be available at:**
http://localhost:8080

**Backend Configuration**

Edit src/main/resources/application.properties:
```bash
spring.datasource.url=jdbc:mysql://localhost:3306/ecopointsdb
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update

# Email settings
spring.mail.host=smtp.your-email-provider.com
spring.mail.port=587
spring.mail.username=your_email@example.com
spring.mail.password=your_email_password
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true
```

### 3️⃣ Frontend Setup (React)
```bash
cd frontend
npm install
npm start
```
**Frontend will be available at:**
http://localhost:3000

**Frontend Configuration**

Create .env inside frontend/:

REACT_APP_API_URL=http://localhost:8080/api


### 🔄 Workflow
- Customer Returns Cover – Barcode is scanned via frontend.

- Backend Processes Return – Validates barcode and updates EcoPoints.

- Points Update – Adds 10 EcoPoints to user’s account.

- Email Notification – Sends total updated EcoPoints to customer.


### 📷 Screenshots
Add screenshots of your UI here.


### 📜 License
This project is for educational and portfolio purposes.







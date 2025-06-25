# 🕒 Timesheet Backend

Bu proje, kullanıcıların zaman çizelgesi (timesheet) oluşturup yönetmesini sağlayan bir web uygulamasının sunucu (backend) tarafıdır. Spring Boot kullanılarak geliştirilmiştir ve JWT ile güvenli giriş-çıkış sistemi içerir.
Bu backend servisi, Angular ile geliştirilen frontend arayüzüyle birlikte çalışmaktadır.


<img src="https://github.com/user-attachments/assets/4be8ce84-1c33-4819-859f-7c1a51ddb282" alt="Timesheet Backend Mimari Diyagramı" width="400"/>



Kullanıcılar:
- Giriş yapabilir, kayıt olabilir
- Timesheet (çalışma saati) girişi yapabilir
- Girdilerini listeleyebilir, güncelleyebilir, dışa aktarabilir

Yöneticiler (Admin):
- Tüm kullanıcıları görebilir
- Tüm timesheet kayıtlarına ulaşabilir
- Arama ve filtreleme yapabilir
- Excel/CSV dışa aktarma işlemleri yapabilir

  ## 🧰 Kullanılan Teknolojiler

  <p align="left">
  <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/java/java-original.svg" width="50"/>
  <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/spring/spring-original.svg" width="50"/>
  <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/hibernate/hibernate-plain.svg" width="50"/>
</p>

Aşağıda, bu backend projesinde kullanılan başlıca teknolojiler listelenmiştir:

- **Java 17** – Backend geliştirme dili
- **Spring Boot 3** – Hızlı ve modern web uygulamaları için çatı (framework)
- **Spring Security** – Kimlik doğrulama ve rol bazlı yetkilendirme
- **JWT (JSON Web Token)** – Kullanıcı oturumlarının güvenli yönetimi
- **Spring Data JPA** – Repository tabanlı veri erişim arayüzü
- **Hibernate ORM** – Java nesnelerini veritabanı tablolarına eşleyen ORM aracı
- **MySQL** – Veritabanı yönetim sistemi
- **Maven** – Proje bağımlılık ve build yönetimi

## 📁 Proje Klasör Yapısı

Aşağıda `com.aksigorta.timesheet` paket yapısı ve alt klasörler listelenmiştir:

```
com.timesheet.timesheet
│
├── config             # Güvenlik ayarları, JWT konfigürasyonları
├── controller         # REST API endpoint sınıfları
├── dto                # Veri transfer nesneleri (Data Transfer Objects)
├── filter             # Request filtreleme işlemleri (örneğin JWT filter)
├── model              # Entity sınıfları (User, Timesheet)
├── repository         # Spring Data JPA repository arayüzleri
├── service            # İş mantığını barındıran servis sınıfları
├── util               # Yardımcı (utility) sınıflar
│
└── TimesheetApplication  # Uygulamanın giriş noktası (main class)
```

Bu yapı, katmanlı bir mimari anlayışıyla oluşturulmuştur. Her klasörün görevi belirgindir ve SOLID prensiplerine uygun geliştirme hedeflenmiştir.

## 🗃️ Veritabanı Yapısı

Proje iki temel tablo üzerinde çalışmaktadır: `users` ve `timesheets`.

### 🧑 users tablosu

Kullanıcı bilgilerini içerir.

| Alan Adı     | Veri Tipi | Açıklama               |
|--------------|-----------|------------------------|
| id           | Long      | Otomatik artan ID      |
| username     | String    | Benzersiz kullanıcı adı|
| email        | String    | Benzersiz e-posta      |
| password     | String    | Şifre (hashlenmiş)     |
| role         | Enum      | USER veya ADMIN        |

### ⏱️ timesheets tablosu

Kullanıcıların günlük/haftalık çalışma saatlerini içerir.

| Alan Adı     | Veri Tipi | Açıklama                       |
|--------------|-----------|--------------------------------|
| id           | Long      | Otomatik artan ID              |
| user_id      | Long      | Bağlı olduğu kullanıcı (FK)    |
| date         | LocalDate | Çalışma tarihi                 |
| start_time   | LocalTime | Başlangıç saati                |
| end_time     | LocalTime | Bitiş saati                    |
| description  | String    | Yapılan işin açıklaması        |


## 🔧 Uygulamanın Kurulumu ve Çalıştırılması

Aşağıdaki adımları izleyerek projeyi kendi ortamınızda çalıştırabilirsiniz:

### 1. Projeyi Klonlayın

```bash
git clone https://github.com/ihalilbesli/timesheet-backend.git
cd timesheet-backend
```

### 2. Veritabanı Ayarları

`application.properties` ya da `application.yml` dosyasında veritabanı bağlantı bilgilerini düzenleyin:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/timesheet
spring.datasource.username=root
spring.datasource.password=root
```

> Gerekirse kendi MySQL bilgilerinize göre değiştirin.

### 3. Bağımlılıkları Yükleyin ve Uygulamayı Başlatın

```bash
./mvnw clean install
./mvnw spring-boot:run
```

Ya da bir IDE (IntelliJ IDEA, Eclipse vs.) üzerinden `TimesheetApplication` sınıfını çalıştırabilirsiniz.

---

Uygulama çalıştıktan sonra backend şu adreste çalışır:  
📍 `http://localhost:8080`


Her türlü soru ve geri bildirim için iletişime geçebilirsiniz.  
📧 **E-posta:** ihalilbesli@gmail.com
🔗 **LinkedIn:** [linkedin.com/in/ibrahim-halil-beşli-3079ab223](https://www.linkedin.com/in/ibrahim-halil-be%C5%9Fli-3079ab223/)



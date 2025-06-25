# 🕒 Timesheet Backend

Bu proje, kullanıcıların zaman çizelgesi (timesheet) oluşturup yönetmesini sağlayan bir web uygulamasının sunucu (backend) tarafıdır. Spring Boot kullanılarak geliştirilmiştir ve JWT ile güvenli giriş-çıkış sistemi içerir.
Bu backend servisi, Angular ile geliştirilen frontend arayüzüyle birlikte çalışmaktadır.

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

Aşağıda, bu backend projesinde kullanılan başlıca teknolojiler listelenmiştir:

- **Java 17** – Backend geliştirme dili
- **Spring Boot 3** – Hızlı ve modern web uygulamaları için framework
- **Spring Security** – Kimlik doğrulama ve yetkilendirme işlemleri
- **JWT (JSON Web Token)** – Güvenli kullanıcı oturumları
- **Spring Data JPA (Hibernate)** – ORM ile veritabanı işlemleri
- **MySQL ** – Veritabanı yönetim sistemi
- **Maven** – Proje bağımlılık ve build yönetimi

  ## 📁 Proje Klasör Yapısı

Aşağıda `com.aksigorta.timesheet` paket yapısı ve alt klasörler listelenmiştir:

<pre lang="markdown"> ## 📁 Proje Klasör Yapısı Aşağıda `com.aksigorta.timesheet` paket yapısı ve alt klasörler listelenmiştir: ``` com.aksigorta.timesheet ├── controller # API endpoint sınıfları │ ├── UserController │ ├── TimesheetController │ └── AdminController ├── service # Servis arayüzleri │ ├── UserService │ ├── TimesheetService │ └── AdminService ├── service.impl # Servis implementasyonları ├── model # Entity sınıfları (User, Timesheet) ├── repository # Spring Data JPA arayüzleri │ ├── UserRepository │ └── TimesheetRepository ├── config # Güvenlik ve JWT yapılandırmaları ``` Bu yapı, katmanlı mimari anlayışıyla oluşturulmuştur. Her klasörün görevi belirgindir ve SOLID prensiplerine uygun geliştirme hedeflenmiştir. </pre>

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

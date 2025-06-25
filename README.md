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

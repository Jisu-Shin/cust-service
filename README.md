# 🖼️ cust-service

OO-SMS 프로젝트의 고객(Customer) 도메인을 담당하는 서비스입니다.  
고객 등록, 조회, 수정 기능을 제공하며, 다른 서비스에서 고객 정보를 활용할 수 있도록 API를 제공합니다.
---

## 🛠 기술 스택

- Java 17
- Spring Boot 3.3
- Spring Data JPA
- H2 Database 
- Spring Cloud (Eureka Client, Config Client 적용예정)
- Docker, Docker Compose
- Swagger (API 문서 자동 생성)

---

## 🧩 주요 기능

- 고객 등록 API
- 고객 정보 조회 API
- 고객 정보 수정 API
- 내부 서비스(view-service 등)와 연동을 위한 고객 데이터 제공
- RESTful 설계 원칙을 준수한 API 작성

---

## 🛢️ 고객 도메인 DB 모델링
고객 서비스는 MSA 구조 내 하나의 통합 데이터베이스를 사용하며,  
다른 도메인과는 약결합(Loose Coupling)된 형태로 서비스 단위만 분리하여 설계되었습니다.

### 📋 ERD

![cust-service-erd](./docs/images/cust-service-erd.png) <!-- ← ERD 이미지 저장 경로 -->
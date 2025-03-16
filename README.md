#  주문 및 재고 관리 시스템

##  프로젝트 개요
이 프로젝트는 **단건 주문 및 엑셀을 통한 대량 주문**을 처리하며, **재고 관리**와 **동시 주문 제어**를 고려하여 설계되었습니다.

###  **주요 기능**
- ✅ **단건 주문 등록 API**
- ✅ **엑셀 주문 등록 API**
- ✅ **재고 확인 및 차감 로직 적용**
- ✅ **Redis 기반 분산 락 적용 (Redisson)**
- ✅ **데이터베이스는 `H2`를 사용하여 쉽게 테스트 가능**
- ✅ **Swagger 적용을 통한 API 문서 제공**

---

## ** 설계에서 중점을 둔 사항**
### **동시 주문 방지 (Redis 기반 분산 락)**
- 동시 주문 시 **재고가 정상적으로 차감되도록** Redis 기반 분산 락을 적용하였습니다.
- **모든 주문을 하나의 락으로 관리**하여 재고가 중복 차감되지 않도록 설계하였습니다.

###  **재고 체크 로직 최적화**
- **모든 상품의 재고를 먼저 체크**한 후 부족한 경우 주문을 차단하여 **불완전한 주문이 저장되는 것을 방지**하였습니다.
- 주문 등록과 재고 차감을 **트랜잭션 단위로 처리**하여 데이터 일관성을 유지하였습니다.

###  **외부 라이브러리 사용**
org.springframework.boot:spring-boot-starter-web	Spring Boot 기반 REST API 개발
org.springframework.boot:spring-boot-starter-data-jpa	JPA를 이용한 데이터베이스 관리
com.h2database:h2	인메모리 H2 데이터베이스 사용
org.apache.poi:poi-ooxml Excel(.xlsx) 파일 업로드 및 파싱
org.springdoc:springdoc-openapi-starter-webmvc-ui		Swagger(OpenAPI) 기반 API 문서 자동화
org.redisson:redisson	Redis 기반 분산 락 적용

 **Swagger UI - http://localhost:8080/swagger-ui.html**  
 **H2 - [http://localhost:8080/swagger-ui.htm](http://localhost:8080/h2-console) id:sa , pw : 빈값**  
---

주문 등록 API 예시
{
  "customerName": "홍길동",
  "customerAddress": "서울특별시 강남구",
  "orderItems": [
    { "productId": 1, "quantity": 2 },
    { "productId": 2, "quantity": 1 }
  ]
}


![image](https://github.com/user-attachments/assets/8245fdd3-23fe-4a20-bfe4-ef982514964d)

# 일정 관리 API 명세서

## 1. 일정 생성 (Create)

### Endpoint

* POST /schedules

### Request Body

    {
    "task": "회의 준비",
    "name": "홍길동",
    "password": "1234"
    }

### Response

    {
    "id": 1,
    "task": "회의 준비",
    "name": "홍길동",
    "createdAt": "2025-02-04T12:00:00",
    "updatedAt": "2025-02-04T12:00:00"
    }

----

## 2. 전체 일정 조회 (Read All)

### Endpoint

* GET /schedules

### Response

    [
    {
    "id": 1,
    "task": "회의 준비",
    "name": "홍길동",
    "createdAt": "2025-02-04T12:00:00",
    "updatedAt": "2025-02-04T14:00:00"
    },
    
    {
    "id": 2,
    "task": "보고서 작성",
    "name": "김철수",
    "createdAt": "2025-02-03T09:00:00",
    "updatedAt": "2025-02-03T10:30:00"
    }
    ]

----

## 3. 특정 일정 조회 (Read One)

### Endpoint

* GET /schedules/{scheduleId}

### Response

    {
    "id": 1,
    "task": "회의 준비",
    "name": "홍길동",
    "createdAt": "2025-02-04T12:00:00",
    "updatedAt": "2025-02-04T14:00:00"
    }

----

## 4. 일정 수정 (Update)

### Endpoint

* PUT /schedules/{scheduleId}

### Request Body

    {
    "task": "회의 장소 변경",
    "name": "홍길동",
    "password": "1234"
    }

### Response

    {
    "id": 1,
    "task": "회의 장소 변경",
    "name": "홍길동",
    "createdAt": "2025-02-04T12:00:00",
    "updatedAt": "2025-02-04T15:00:00"
    }

----

## 5. 일정 삭제 (Delete)

### Endpoint

* DELETE /schedules/{scheduleId}

### Query Parameter

* password

### Example Request

    {
    "password": 1234
    }

### Response

* 성공: 200 No Content

* 실패 (비밀번호 불일치): 400 Bad Request


    {
    "error": "비밀번호가 일치하지 않습니다."
    }

## 에러 응답 예시

    {
    "timestamp": "2025-02-04T00:47:20.021+00:00",
    "status": 500,
    "error": "Internal Server Error",
    "path": "/schedules/2"
    }

## EDR
https://www.erdcloud.com/d/xrmJfbG48vKWJW53D

# Assignment-Project-KeyWord
#Json
GEO Test Task
---------------------------------
CRUD for Authors
----------------
Add Author
----------
URL:- http://localhost:8080/author/v1/add
Method:- POST
Request Body
------------
{
  "name": "Rakesh Sharma"
}
Response Body
-------------
{
  "responseCode": "S0001",
  "responseDescription": "Added successfully!",
  "response": {
    "id": 13
  }
}
------------------------------------------------
Update Author
----------
URL:- http://localhost:8080/author/v1/update
Method:- PUT
Request Body
------------
{
  "id": 13,
  "name": "Rakesh Kumar"
}
Response Body
-------------
{
  "responseCode": "S0001",
  "responseDescription": "Updated successfully!",
  "response": {
    "id": 13
  }
}
--------------------------------------------------
Get Author By Id
----------
URL:- http://localhost:8080/author/v1/13
Method:- GET
Path variable {id}
Response Body
-------------
{
  "responseCode": "S0001",
  "responseDescription": "Success",
  "response": {
    "results": {
      "id": 13,
      "name": "Rakesh Kumar"
    }
  }
}
-------------------------------------------------------
Get Authors
----------
URL:- http://localhost:8080/author/v1/list
Method:- GET
Response Body
-------------
{
  "responseCode": "S0001",
  "responseDescription": "Success",
  "response": {
    "results": [
      {
        "id": 7,
        "name": "Faiz Akram"
      },
      {
        "id": 8,
        "name": "Faiz Akram"
      },
      {
        "id": 13,
        "name": "Rakesh Kumar"
      }
    ]
  }
}
--------------------------------------------------
Delete Author By Id
----------
URL:- http://localhost:8080/author/v1/13
Method:- GET
Path variable {id}
Response Body
-------------
{
  "responseCode": "S0001",
  "responseDescription": "Deleted successfully!",
  "response": {
    "id": 13
  }
}
------------------------------X-------------------------
CRUD for publications
----------------------------
Some of the ids are master data that have also api's
Add Publications (Comics)
-------------------------
URL:- http://localhost:8080/publication/v1/add
Method:- POST
Request Body
------------
{
  "authors": [
{
      "id": 47
    },
{
      "id": 52
    }
  ],
  "hero": "Hero Name",
  "publicationEnumId": 2, // Comics (It's a Master Data)
  "title": "Title One",
  "year": "2017"
}
Response Body
--------------
{
  "responseCode": "S0001",
  "responseDescription": "Added successfully! ",
  "response": {
    "id": 14
  }
}

Add Publications (Book)
----------
URL:- http://localhost:8080/publication/v1/add
Method:- POST
Request Body
------------
{
  "authors": [
{
      "id": 47
    },
{
      "id": 52
    }
  ],
  "bookEnumId": 6, // Drama (It's a Master Data)
  "publicationEnumId": 3,//Book (It's a Master Data)
  "title": "Title One Daram",
  "year": "2017"
}
Response Body
--------------
{
  "responseCode": "S0001",
  "responseDescription": "Added successfully! ",
  "response": {
    "id": 15
  }
}
Add Publications (Magazine)
----------
URL:- http://localhost:8080/publication/v1/add
Method:- POST
Request Body
------------
"authors": [
{
      "id": 47
    },
{
      "id": 52
    }
  ],
  "magazineEnumId": 4, // Printed (It's a Master Data)
  "publicationEnumId": 1,//Magazine (It's a Master Data)
  "title": "Title One Printed",
  "year": "2018"
}
Response Body
--------------
{
  "responseCode": "S0001",
  "responseDescription": "Added successfully! ",
  "response": {
    "id": 16
  }
}
--------------------------------
Update Publication
------------------
URL:- http://localhost:8080/publication/v1/add
Method:- PUT
Request Body (Giving only example for this)
------------
"authors": [
{
      "id": 47
    },
{
      "id": 52
    }
  ],
  "hero": "Old Book",
  "id": 15,
  "publicationEnumId": 2,//Comics
  "title": "Old Book",
  "year": "2009"
}
Response Body
--------------
{
  "responseCode": "S0001",
  "responseDescription": "Updated successfully!",
  "response": {
    "id": 15
  }
}
--------------------------------------------
Get Publication By Id
------------------
URL:- http://localhost:8080/publication/v1/15
Method:- GET
Pathvariable {id}
Response Body 
------------
{
  "responseCode": "S0001",
  "responseDescription": "Success",
  "response": {
    "results": {
      "id": 15,
      "author": {
        "id": 7,
        "name": "Faiz Akram"
      },
      "title": "Old Book",
      "year": "2009",
      "publicationEnumId": 2,
      "hero": "Old Book"
    }
  }
}
---------------------------------------------
Get Publications
------------------
URL:- http://localhost:8080/publication/v1/list
Method:- GET
Response Body 
------------
{
  "responseCode": "S0001",
  "responseDescription": "Success",
  "response": {
    "results": [
      {
        "id": 14,
         "authors": [
          {
            "id": 47,
            "name": "Faiz"
          }
        ],
        "title": "Title One",
        "year": "2017",
        "publicationEnumId": 2,
        "magazineEnumId": 4
      },
      {
        "id": 15,
         "authors": [
          {
            "id": 47,
            "name": "Faiz"
          }
        ],
        "title": "Old Book",
        "year": "2009",
        "publicationEnumId": 2,
        "hero": "Old Book"
      }
    ]
  }
}



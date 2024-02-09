
#  <img src="img.png"> Automation API that sends the company's Notion task list by email every morning 🤖

> 회사에서 노션으로 업무 관리해서 매일 아침 10분을 업무파악에 시간을 투자, 출근 시간에 빠르게 업무를 파악하기 위해 만든 API


### 프로젝트 시작 이유
- 업무를 노션으로 관리하며 매일 아침에 10분을 업무 파악을 위해 시간을 투자, 매일 출근길에 이메일을 통해서 업무 파악



### Notion API Response 구조
```json
{
    "object": "list",
    "results": [
        {
            "object": "page",
            "id": "",
            "created_time": "",
            "last_edited_time": "",
            "created_by": {
                "object": "user",
                "id": ""
            },
            "last_edited_by": {
                "object": "user",
                "id": ""
            },
            "cover": null,
            "icon": null,
            "parent": {
                "type": "",
                "database_id":"" 
            },
            "archived": false,
            "properties": {
                "우선순위": {
                    "id": "%3CHOA",
                    "type": "select",
                    "select": {
                        "id": ";hjo",
                        "name": "2)Medium",
                        "color": "purple"
                    }
                },
                "분류": {
                    "id": "ED_C",
                    "type": "select",
                    "select": {
                        "id": "pLTn",
                        "name": "",
                        "color": "yellow"
                    }
                },
                "처리": {
                    "id": "GmTR",
                    "type": "people",
                    "people": [
                        {
                            "object": "user",
                            "id": "",
                            "name": "김 무건",
                            "avatar_url": "",
                            "type": "person",
                            "person": {
                                "email": ""
                            }
                        }
                    ]
                },
                "요청": {
                    "id": "KANo",
                    "type": "people",
                    "people": [
                        {
                            "object": "user",
                            "id": "",
                            "name": "",
                            "avatar_url": "",
                            "type": "person",
                            "person": {
                                "email": ""
                            }
                        }
                    ]
                },
                "요청날짜": {
                    "id": "iaSl",
                    "type": "date",
                    "date": {
                        "start": "2024-01-14",
                        "end": null,
                        "time_zone": null
                    }
                },
                "상태": {
                    "id": "n%5CAz",
                    "type": "select",
                    "select": {
                        "id": "LefA",
                        "name": "신규",
                        "color": "default"
                    }
                },
                "기한": {
                    "id": "unEw",
                    "type": "date",
                    "date": {
                        "start": "2024-01-22",
                        "end": null,
                        "time_zone": null
                    }
                },
                "Name": {
                    "id": "title",
                    "type": "title",
                    "title": [
                        {
                            "type": "text",
                            "text": {
                                "content": "2번 제목",
                                "link": null
                            },
                            "annotations": {
                                "bold": false,
                                "italic": false,
                                "strikethrough": false,
                                "underline": false,
                                "code": false,
                                "color": "default"
                            },
                            "plain_text": "2번 제목",
                            "href": null
                        }
                    ]
                }
            },
            "url": "",
            "public_url": null
        }
    ],
    "next_cursor": null,
    "has_more": false,
    "type": "",
    "page_or_database": {},
    "developer_survey": "",
    "request_id": ""
}
```
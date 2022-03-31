# Lawn Mower

## Instructions
Clone the repo and open the project with your favorite IDE
* `mvn clean install` to build the project
* `jav -jar lawnmower` to run the project from the  build output directory

## Sample data
Request payload  
Send http `POST` request to `127.0.0.1:8080/mower/process`
```json
{
  "dimensions": {
    "width": 5,
    "height": 5
  },
  "mowers": [
    {
      "position": {
        "x": 1,
        "y": 2,
        "orientation": "N"
      },
      "instructions": "GAGAGAGAA"
    },
    {
      "position": {
        "x": 3,
        "y": 3,
        "orientation": "E"
      },
      "instructions": "AADAADADDA"
    }
  ]
}
```

Expected Result
```json
{
  "positions": [
    {
      "x": 1,
      "y": 3,
      "orientation": "N"
    },
    {
      "x": 5,
      "y": 1,
      "orientation": "E"
    }
  ]
}
```

## Example call with `curl`
```curl
curl --location --request POST '127.0.0.1:8080/mower/process' \
--header 'Content-Type: application/json' \
--data-raw '{
    "dimensions": {
        "width": 5,
        "height": 5
    },
    "mowers": [
        {
            "position": {
                "x": 1,
                "y": 2,
                "orientation": "N"
            },
            "instructions": "GAGAGAGAA"
        },
        {
            "position": {
                "x": 3,
                "y": 3,
                "orientation": "E"
            },
            "instructions": "AADAADADDA"
        }
    ]
}
'
```
# TO DO
* Logging management
* Rework `LawnmowerService` with **Strategy** pattern
* Unit tests for `LawnmowerService`
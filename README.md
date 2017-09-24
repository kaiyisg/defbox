# defbox

## b4a

To install, follow the tutorial here:
https://docs.back4app.com/docs/integrations/command-line-interface/creating-a-parse-app/

To deploy, in `/b4a` run

```
b4a deploy
```

```
curl -X POST \

-H "X-Parse-Application-Id: ${APPLICATION_ID}" \

-H "X-Parse-REST-API-Key: ${REST_API_KEY}" \

-H "Content-Type: application/json" \ -d '{}' \

https://parseapi.back4app.com/functions/hello
```

## Example config

```json
cors:
  cors-mappings:
    '/api/**': 
      allowed-origins: http://localhost
      allowed-methods: GET,POST
      allowed-headers: Content-type
      exposed-headers: Content-type
      allow-credentials: true
      max-age: 3600
```
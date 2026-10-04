# SmartContactManager

## Local configuration

`src/main/resources/application.properties` reads database, login, OAuth, and
Cloudinary credentials from environment variables. Set these variables in your
environment before running the application; do not put real credential values
in the repository:

- `DB_USERNAME` and `DB_PASSWORD`
- `APP_USER_PASSWORD`
- `GOOGLE_CLIENT_ID` and `GOOGLE_CLIENT_SECRET`
- `GITHUB_CLIENT_ID` and `GITHUB_CLIENT_SECRET`
- `CLOUDINARY_CLOUD_NAME`, `CLOUDINARY_API_KEY`, and `CLOUDINARY_API_SECRET`

In PowerShell, `$env:VARIABLE_NAME = "value"` sets a variable for the current
terminal session. Use your operating system's environment-variable settings
for values that should persist across sessions.
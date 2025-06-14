# Run with docker
## dev
1. Run the MySQL Server Container
    - Go to deployment > dev folder
    - Supply the right environment variables in .env first!
    - Run this command
```
docker compose up -d
```
2. Run the local Philippine Location API project
   - Supply the right environment variables in .env.development (deployment/dev) first
   - Supply the right environment variables in .env.test (test/resources) first
   - Add it to IDE environment variables

## prod
1. Run the Project
    - Go to deployment > prod folder
    - Supply the right environment variables in .env first!
    - Run this command
```
docker compose up -d
```
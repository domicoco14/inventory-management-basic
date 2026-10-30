# Inventory Reports: Frontend (Group 4)

A standalone Vaadin Flow app for the **Report statistics** screen. It has no code in common with the backend project,
`inventory-reports-backend` (Group 8). It reads everything from that project's REST API.

| Part | Standard |
|---|---|
| Java | 25 LTS |
| Spring Boot | 4.1.2 |
| Vaadin Flow | 25.3.3 |
| Build | Maven 3.9.11 through the wrapper (`mvnw`), nothing to install |

## Run it

Start the backend first (`./mvnw spring-boot:run` in its folder), then:

```
./mvnw spring-boot:run
```

Open http://localhost:8081. The first start is slow because Vaadin builds its front end. Run the tests with
`./mvnw test`. There is no login: login and registration belong to another group.

If the backend is not running, the screen shows a "reporting service is not reachable" panel with a Try again button.

### Settings

In `src/main/resources/application.properties`.

| Setting | Environment variable | Default |
|---|---|---|
| Backend address | `BACKEND_URL` | `http://localhost:8080` |

## The screen

`/` is **Report statistics**: four today-vs-yesterday cards (sales, total orders, revenue, customers), an Orders vs Sales
bar chart with 7, 14 and 30 day ranges, and a Top selling products donut.

The header and sidebar follow the Figma frame: search, bell and an avatar on the right, and a narrow dark sidebar with
icon-over-label items. The **Sales** item opens Report statistics. In Stock, Products, Orders and Users are
placeholders that belong to other groups. Search and the bell are visual only.

## Layout

```
src/main/java/com/inventory/frontend/
  backend/      BackendClient (the only code that calls the API) and the DTO records that mirror its JSON
  config/       the backend client settings
  ui/           MainLayout, the Report statistics screen, the placeholders, and ui/components (charts, KPI card, shared panels)
src/main/resources/META-INF/resources/styles/app.css   all styling
```

Charts are inline SVG built in `Charts.java`, so there is no chart library to install.

If the backend adds or changes a field, update the matching record in `backend/dto/` and its test in `BackendClientTest`.

## Not built yet

Login and registration, the Dashboard, the In Stock, Products, Orders and Users screens, and working search and
notifications.

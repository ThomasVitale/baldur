# Baldur

## Development environment

Activate the development environment:

```shell
devbox shell
```

Open the project in VS Code:

```shell
code .
```

## Running the application

If you're using the native Ollama application, run the application as follows:

```shell
./gradlew bootRun
```

Alternatively, you can use the [Arconia CLI](https://arconia.io/docs/arconia-cli/latest/):

```shell
arconia dev
```

Under the hood, the Arconia framework will automatically spin up a [PostgreSQL](https://arconia.io/docs/arconia/latest/dev-services/postgresql/) database and a [Grafana LGTM](https://arconia.io/docs/arconia/latest/dev-services/lgtm/) observability platform using Testcontainers (see [Arconia Dev Services](https://arconia.io/docs/arconia/latest/dev-services/) for more information).

If instead you want to rely on the [Ollama Dev Service](https://arconia.io/docs/arconia/latest/dev-services/ollama/) via Testcontainers, run the application as follows.

```shell
./gradlew bootRun -Darconia.dev.services.ollama.enabled=true
```

Either way, the application will be accessible at http://localhost:8080.

## Accessing Grafana

The application logs will show you the URL where you can access the Grafana observability platform.

```logs
...o.t.grafana.LgtmStackContainer: Access to the Grafana dashboard: http://localhost:<port>
```

By default, logs, metrics, and traces are exported via OTLP using the HTTP/Protobuf format.

In Grafana, you can query the telemetry from the "Drilldown" and "Explore" sections.

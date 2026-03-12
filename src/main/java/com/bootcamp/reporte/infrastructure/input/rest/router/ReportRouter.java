package com.bootcamp.reporte.infrastructure.input.rest.router;

import com.bootcamp.reporte.infrastructure.input.rest.handler.ReportHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springdoc.core.annotations.RouterOperation;
import org.springdoc.core.annotations.RouterOperations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class ReportRouter {

    @Bean
    @RouterOperations({
            @RouterOperation(
                    path = "/api/reports",
                    method = RequestMethod.POST,
                    beanClass = ReportHandler.class,
                    beanMethod = "saveReport",
                    operation = @Operation(
                            operationId = "saveReport",
                            summary = "Crear reporte",
                            description = "Genera y guarda un reporte",
                            requestBody = @RequestBody(
                                    required = true,
                                    content = @Content(
                                            mediaType = "application/json"
                                    )
                            ),
                            responses = {
                                    @ApiResponse(responseCode = "200", description = "Reporte creado"),
                                    @ApiResponse(responseCode = "400", description = "Solicitud inválida")
                            }
                    )
            ),
            @RouterOperation(
                    path = "/api/reports/most-popular",
                    method = RequestMethod.GET,
                    beanClass = ReportHandler.class,
                    beanMethod = "getMostPopular",
                    operation = @Operation(
                            operationId = "getMostPopularReports",
                            summary = "Consultar más populares",
                            description = "Obtiene los reportes o bootcamps más populares",
                            responses = {
                                    @ApiResponse(responseCode = "200", description = "Consulta exitosa")
                            }
                    )
            )
    })
    public RouterFunction<ServerResponse> reportRoutes(ReportHandler handler) {
        return route(POST("/api/reports").and(accept(MediaType.APPLICATION_JSON)), handler::saveReport)
                .andRoute(GET("/api/reports/most-popular"), handler::getMostPopular);
    }
}
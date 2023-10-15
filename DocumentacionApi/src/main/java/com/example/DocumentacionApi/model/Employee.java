package com.example.DocumentacionApi.model;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

public class Employee {

    @Schema(example = "4", description = "Identificador clave primaria empleado numérico")
    private Long id;

    @Schema(example = "Fran", description = "Nombre completo empleado")
    private String name;

    @Schema(example = "2003/02/07", description = "Fecha nacimiento yyyy-MM-dd")
    private LocalDateTime birthDate;

    @Schema(example = "4000", description = "Salario anual bruto del empleado")
    private Double salary;

    @Schema(example = "False", description = "Situación casado si o no del empleado")
    private Boolean married;

    public Employee(Long id, String name, LocalDateTime birthDate, Double salary, Boolean married) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
        this.salary = salary;
        this.married = married;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDateTime birthDate) {
        this.birthDate = birthDate;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Boolean getMarried() {
        return married;
    }

    public void setMarried(Boolean married) {
        this.married = married;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthDate=" + birthDate +
                ", salary=" + salary +
                ", married=" + married +
                '}';
    }
}

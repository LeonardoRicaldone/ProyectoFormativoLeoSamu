CREATE TABLE tbPacientes(
idPaciente int primary key,
nombre varchar2(50) NOT NULL,
apellido varchar2(50)not null,
edad int NOT NULL,
enfermedad varchar2(50)NOT NULL,
numero_habitacion int not null,
numero_cama int not null,
medicamentos_asignados varchar(100)not null,
fecha_nacimiento varchar2(10),
hora_aplicacion_medicamento varchar2(5)
);

CREATE SEQUENCE identity_pacientes
START WITH 1
INCREMENT BY 1
NOCACHE;

CREATE OR REPLACE TRIGGER trg_before_insert_pacientes
BEFORE INSERT ON tbPacientes
FOR EACH ROW
BEGIN
    SELECT identity_pacientes.NEXTVAL INTO :new.idPaciente FROM dual;
END;

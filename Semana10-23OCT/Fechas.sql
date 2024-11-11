select * from CLIENTE;
go

select * from EMPLEADO;
go

select * from HABITACION;
go

select * from reserva;
go


insert into reserva(FECHA_INGRESO)
values(convert(datetime,?,103))

declare @fecha1 varchar(10), @fecha2 varchar(10);
set @fecha1 = '15/10/2024';
set @fecha2 = '16/10/2024';
select datediff(day,convert(datetime,@fecha1,103),convert(datetime,@fecha2,103)) dias;
go

INSERT INTO RESERVA(IDEMPLEADO,IDHABITACION,IDCLIENTE,IDESTADORESERVA,COSTO,FECHA_INGRESO,FECHA_SALIDA)
VALUES ( ?, ?, ?, 1, ?, convert(datetime,?,103), convert(datetime,?,103))
go

select * from ESTADO_RESERVA;
go

select * from ESTADO_HABITACION;
go

update HABITACION set IDESTADOHABITACION=4 where IDHABITACION=?








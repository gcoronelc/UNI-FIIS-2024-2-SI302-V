

select cur_id, alu_id, emp_id, mat_tipo, 
convert(varchar(20), mat_fecha, 103 ) fecha,
mat_precio, mat_cuotas, mat_nota
from matricula 
where cur_id=?
go

select cur_precio from curso where cur_id=1;
go

select * from matricula;
go

insert into matricula(cur_id,alu_id,emp_id,mat_tipo,mat_fecha,mat_precio,mat_cuotas)
values(?,?,?,?,GETDATE(),?,?)
GO


select * from curso;
go

update curso 
set cur_matriculados = cur_matriculados + 1
where cur_id = ?;
go

select * from curso where cur_id=1;
select * from matricula where cur_id=1;
go

with
t1 as (
	select cur_id, alu_id, emp_id, mat_tipo, 
	convert(varchar(20), mat_fecha, 103 ) fecha,
	mat_precio, mat_cuotas, mat_nota
	from matricula 
	where cur_id=1
),
t2 as (
	select alu_id, sum(pag_importe) pago
	from pago 
	where cur_id = 1
	group by alu_id
)
select t1.cur_id, t1.alu_id, t1.emp_id,
t1.mat_tipo, mat_cuotas, t1.fecha, t1.mat_precio,
ISNULL(t1.mat_nota,0) nota, ISNULL(t2.pago,0) pago, 
t1.mat_precio - ISNULL(t2.pago,0) deuda
from t1 left join t2 on t1.alu_id=t2.alu_id
go




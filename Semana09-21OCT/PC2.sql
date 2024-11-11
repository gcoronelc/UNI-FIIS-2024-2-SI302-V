
SELECT 
	T.idtipo, T.descripcion, 
	SUM(V.cantidad) AS cantidadTotal, 
	SUM(V.total) AS ventasTotal 
FROM TIPO T
LEFT JOIN PUBLICACION P ON T.idtipo = P.idtipo 
LEFT JOIN VENTA V ON P.idpublicacion = V.idpublicacion 
GROUP BY T.idtipo, T.descripcion
go

SELECT TOP 1 * FROM PUBLICACION;
select * from PROMOCION;
SELECT * FROM VENTA ORDER BY 1 DESC;
GO

select * from CONTROL;
go

declare @cant int;
set @cant = 5;
select porcentaje from PROMOCION where @cant>=cantmin and @cant<=cantmax;
go




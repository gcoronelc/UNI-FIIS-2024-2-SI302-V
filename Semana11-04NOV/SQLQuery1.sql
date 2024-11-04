select int_contitem, int_contlongitud 
from contador where vch_conttabla='cliente';

begin tran

update contador 
set int_contitem = 40
where vch_conttabla='cliente';

rollback;


insert into cliente(chr_cliecodigo, vch_cliepaterno, 
vch_cliematerno, vch_clienombre, chr_cliedni, 
vch_clieciudad, vch_cliedireccion, vch_clietelefono,
vch_clieemail) values(?,?,?,?,?,?,?,?,?);
go

update cliente set 
vch_cliepaterno=?, vch_cliematerno=?,
vch_clienombre=?, chr_cliedni=?, 
vch_clieciudad=?, vch_cliedireccion=?, 
vch_clietelefono=?, vch_clieemail=?
where chr_cliecodigo=?;
go

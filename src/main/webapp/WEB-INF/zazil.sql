create database zazil;
use zazil;

create table roles(
	id int auto_increment not null,
    nombre_rol varchar(50),
	primary key (id)
);

create table usuario(
	id int auto_increment not null,
    nombre varchar(50),
    nombre2 varchar(50),
    apellido varchar(50),
    apellido2 varchar(50),
    sexo int,
    fecha_nacimiento date,
    correo varchar(60),
    contra text,
    codigo text,
    rol int,
    ultimo_login datetime,
    primary key (id),
    foreign key (rol) references roles(id)
);

create table cliente(
	id int auto_increment not null,
    nombre varchar(50),
    nombre2 varchar(50),
    apellido varchar(50),
    apellido2 varchar(50),
    correo varchar(60),
    telefono varchar(30),
    rfc varchar(15),
    pais int,
    estado int,
    municipio int,
    colonia int,
    direccion text,
    latitud double,
    longitud double,
    primary key (id),
	foreign key (pais) references paises(id),
    foreign key (estado) references estados(id),
    foreign key (municipio) references municipios(id),
    foreign key (colonia) references colonias(id)
);

create table distribuidor(
	id int auto_increment not null,
    nombre_distribuidor varchar(60),
    nombre_contacto varchar(80),
    telefono varchar(20),
    telefono2 varchar(20),
    correo1 varchar(50),
    correo2 varchar(50),
    pais int,
    estado int, 
    municipio int,
    colonia int,
	primary key (id),
    foreign key (pais) references paises(id),
    foreign key (estado) references estados(id),
    foreign key (municipio) references municipios(id),
    foreign key (colonia) references colonias(id)
);

create table categoria(
	id int not null auto_increment,
    nombre varchar(80),
    primary key (id)
);

create table subcategoria(
	id int not null auto_increment,
    nombre varchar(80),
    categoria int,
    primary key (id),
    foreign key (categoria) references categoria(id)
);

create table producto(
	id int not null auto_increment,
    nombre varchar(100),
    imagen text,
    lote varchar(20),
    caducidad date,
    precio_costo decimal(10,2),
    precio_venta decimal(10,2),
    marca varchar(60),
    distribuidor int,
    modelo varchar(60),
    tipo_contenido varchar(50),
    contenido varchar(30),
    subcategoria int,
    categoria int,
	primary key (id),
    foreign key (distribuidor) references distribuidor(id),
    foreign key (categoria) references categoria(id),
    foreign key (subcategoria) references subcategoria(id)
);

create table bodega(
	id int not null auto_increment,
    nombre varchar(50),
    primary key (id)
);

create table inventario(
	id int not null auto_increment,
    producto int,
    cantidad int,
    origen int,
    primary key (id),
    foreign key (producto) references producto(id),
    foreign key (origen) references bodega(id)
);

create table tipos_envios(
	id int not null auto_increment,
    tipo varchar(50),
    primary key (id)
);

create table metodos_pagos(
	id int not null auto_increment,
    metodo varchar(50),
    primary key (id)
);

create table pedido(
	id int not null auto_increment,
    cliente int,
    fecha datetime,
    primary key (id),
    foreign key (cliente) references cliente(id)
);

create table detalle_pedido(
	id int not null auto_increment,
    pedido int,
    producto int,
    cantidad int,
    fecha_agregado datetime,
    primary key (id),
    foreign key (pedido) references pedido(id),
    foreign key (producto) references producto(id)
);

create table ventas(
	id int not null auto_increment,
    vendedor int,
    fecha datetime,
    cliente int,
    pedido int,
    metodo_pago int,
    tipo_envio int,
    primary key (id),
    foreign key (vendedor) references usuario(id),
	foreign key (cliente) references cliente(id),
    foreign key (pedido) references pedido(id),
    foreign key (tipo_envio) references tipos_envios(id),
    foreign key (metodo_pago) references metodos_pagos(id)
);

insert into roles(nombre_rol) values
("admin"),
("inventario"),
("ventas"),
("compras"),
("publicidad");

insert into metodos_pagos(metodo) values
("Transferencia"),
("Oxxo"),
("Efectivo"),
("Link"),
("Debito"),
("Credito, contado"),
("Credito, 3 meses"),
("Credito, 6 meses"),
("Credito, 9 meses"),
("Credito, 12 meses"),
("Retiro de efectivo");

insert into tipos_envios(tipo) values
("Fisico"),
("Paqueteria"),
("Uber");

select usuario.*, roles.nombre_rol from usuario join roles on usuario.rol = roles.id;

select * from distribuidor;
insert into distribuidor(
	nombre_distribuidor,
    nombre_contacto,
    telefono, telefono2, correo1, correo2,
    pais, estado, municipio, colonia
) values
("placeHolder","placeHolder","placeHolder","placeHolder","placeHolder","placeHolder",1,1,1001,10011);
insert into categoria(nombre) values("placeHolder");
insert into subcategoria(nombre, categoria) values("placeHolder",1);

insert into producto(
	nombre, imagen, lote, caducidad, precio_costo,
    precio_venta, marca, distribuidor, modelo, tipo_contenido,
    contenido, subcategoria, categoria
) values
("p1", "img", "lote", CURDATE(), 10, 10.10, "marca",
2,"model","tipo","2ml", 1, 1);

select * from producto as p 
join distribuidor as d on p.distribuidor = d.id 
join paises as pa on d.pais = pa.id 
join estados as es on d.estado = es.id 
join municipios as mu on d.municipio = mu.id 
join colonias as col on d.colonia = col.id 
join subcategoria as sub on p.subcategoria = sub.id 
join categoria as cat on p.categoria = cat.id ; 
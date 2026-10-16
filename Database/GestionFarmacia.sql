IF DB_ID(N'GestionFarmacia') IS NULL
BEGIN
    CREATE DATABASE GestionFarmacia;
END;
GO
USE GestionFarmacia;
GO

IF OBJECT_ID(N'dbo.CategoriaMedicamento', N'U') IS NULL
BEGIN
    CREATE TABLE dbo.CategoriaMedicamento (
        idCategoria INT IDENTITY(1,1) PRIMARY KEY,
        nombre NVARCHAR(100) NOT NULL,
        descripcion NVARCHAR(255)
    );
END;
GO

IF OBJECT_ID(N'dbo.Medicamento', N'U') IS NULL
BEGIN
    CREATE TABLE dbo.Medicamento (
        idMedicamento INT IDENTITY(1,1) PRIMARY KEY,
        nombre NVARCHAR(150) NOT NULL,
        presentacion NVARCHAR(100),
        precio DECIMAL(10,2) NOT NULL,
        existencia INT NOT NULL,
        requiereReceta BIT NOT NULL,
        idCategoria INT NOT NULL,
        CONSTRAINT FK_Medicamento_Categoria FOREIGN KEY (idCategoria)
            REFERENCES dbo.CategoriaMedicamento(idCategoria),
        CONSTRAINT CK_Medicamento_Precio CHECK (precio >= 0),
        CONSTRAINT CK_Medicamento_Existencia CHECK (existencia >= 0)
    );
END;
GO

IF OBJECT_ID(N'dbo.Proveedor', N'U') IS NULL
BEGIN
    CREATE TABLE dbo.Proveedor (
        idProveedor INT IDENTITY(1,1) PRIMARY KEY,
        nombre NVARCHAR(150) NOT NULL,
        telefono NVARCHAR(20),
        correo NVARCHAR(100),
        direccion NVARCHAR(255)
    );
END;
GO

IF OBJECT_ID(N'dbo.Compra', N'U') IS NULL
BEGIN
    CREATE TABLE dbo.Compra (
        idCompra INT IDENTITY(1,1) PRIMARY KEY,
        fecha DATE NOT NULL,
        total DECIMAL(12,2) NOT NULL,
        idProveedor INT NOT NULL,
        CONSTRAINT FK_Compra_Proveedor FOREIGN KEY (idProveedor)
            REFERENCES dbo.Proveedor(idProveedor),
        CONSTRAINT CK_Compra_Total CHECK (total >= 0)
    );
END;
GO

IF OBJECT_ID(N'dbo.DetalleCompra', N'U') IS NULL
BEGIN
    CREATE TABLE dbo.DetalleCompra (
        idDetalleCompra INT IDENTITY(1,1) PRIMARY KEY,
        idCompra INT NOT NULL,
        idMedicamento INT NOT NULL,
        cantidad INT NOT NULL,
        costo DECIMAL(10,2) NOT NULL,
        CONSTRAINT FK_DetalleCompra_Compra FOREIGN KEY (idCompra)
            REFERENCES dbo.Compra(idCompra),
        CONSTRAINT FK_DetalleCompra_Medicamento FOREIGN KEY (idMedicamento)
            REFERENCES dbo.Medicamento(idMedicamento),
        CONSTRAINT CK_DetalleCompra_Cantidad CHECK (cantidad > 0),
        CONSTRAINT CK_DetalleCompra_Costo CHECK (costo >= 0)
    );
END;
GO

IF OBJECT_ID(N'dbo.Cliente', N'U') IS NULL
BEGIN
    CREATE TABLE dbo.Cliente (
        idCliente INT IDENTITY(1,1) PRIMARY KEY,
        nombre NVARCHAR(150) NOT NULL,
        telefono NVARCHAR(20),
        correo NVARCHAR(100),
        direccion NVARCHAR(255)
    );
END;
GO

IF OBJECT_ID(N'dbo.RecetaMedica', N'U') IS NULL
BEGIN
    CREATE TABLE dbo.RecetaMedica (
        idReceta INT IDENTITY(1,1) PRIMARY KEY,
        idCliente INT NOT NULL,
        medico NVARCHAR(150) NOT NULL,
        fecha DATE NOT NULL,
        medicamentosAutorizados NVARCHAR(500),
        CONSTRAINT FK_RecetaMedica_Cliente FOREIGN KEY (idCliente)
            REFERENCES dbo.Cliente(idCliente)
    );
END;
GO

IF OBJECT_ID(N'dbo.Venta', N'U') IS NULL
BEGIN
    CREATE TABLE dbo.Venta (
        idVenta INT IDENTITY(1,1) PRIMARY KEY,
        idCliente INT NOT NULL,
        fecha DATE NOT NULL,
        total DECIMAL(12,2) NOT NULL,
        CONSTRAINT FK_Venta_Cliente FOREIGN KEY (idCliente)
            REFERENCES dbo.Cliente(idCliente),
        CONSTRAINT CK_Venta_Total CHECK (total >= 0)
    );
END;
GO

IF OBJECT_ID(N'dbo.DetalleVenta', N'U') IS NULL
BEGIN
    CREATE TABLE dbo.DetalleVenta (
        idDetalleVenta INT IDENTITY(1,1) PRIMARY KEY,
        idVenta INT NOT NULL,
        idMedicamento INT NOT NULL,
        cantidad INT NOT NULL,
        precio DECIMAL(10,2) NOT NULL,
        CONSTRAINT FK_DetalleVenta_Venta FOREIGN KEY (idVenta)
            REFERENCES dbo.Venta(idVenta),
        CONSTRAINT FK_DetalleVenta_Medicamento FOREIGN KEY (idMedicamento)
            REFERENCES dbo.Medicamento(idMedicamento),
        CONSTRAINT CK_DetalleVenta_Cantidad CHECK (cantidad > 0),
        CONSTRAINT CK_DetalleVenta_Precio CHECK (precio >= 0)
    );
END;
GO

IF OBJECT_ID(N'dbo.Empleado', N'U') IS NULL
BEGIN
    CREATE TABLE dbo.Empleado (
        idEmpleado INT IDENTITY(1,1) PRIMARY KEY,
        nombre NVARCHAR(150) NOT NULL,
        telefono NVARCHAR(20),
        correo NVARCHAR(100),
        cargo NVARCHAR(100) NOT NULL
    );
END;
GO

SELECT TABLE_NAME
FROM INFORMATION_SCHEMA.TABLES
WHERE TABLE_TYPE = 'BASE TABLE'
  AND TABLE_SCHEMA = 'dbo'
ORDER BY TABLE_NAME;
GO

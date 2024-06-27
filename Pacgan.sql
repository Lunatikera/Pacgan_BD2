DELIMITER //

CREATE TRIGGER trg_insertar_pago_estatus
AFTER INSERT ON tblPagos
FOR EACH ROW
BEGIN
    INSERT INTO tblPago_Estatus (fechaHora, mensaje, id_estatus, id_pago)
    VALUES (NOW(), 'Nuevo pago creado', 1, NEW.id_pago);
END//

DELIMITER //

CREATE TRIGGER trg_insertar_pago_estatus_after_update
AFTER UPDATE ON tblPagos
FOR EACH ROW
BEGIN
    IF NEW.id_pago <> OLD.id_pago OR NEW.COMPROBANTE <> OLD.COMPROBANTE OR NEW.monto <> OLD.monto OR NEW.id_beneficiario <> OLD.id_beneficiario 
        OR NEW.id_cuentaBancaria <> OLD.id_cuentaBancaria OR NEW.id_tipoPago <> OLD.id_tipoPago THEN
        INSERT INTO tblPago_Estatus (fechaHora, mensaje, id_estatus, id_pago)
        VALUES (NOW(), 'Pago actualizado', 5, NEW.id_pago);
    END IF;
END //

DELIMITER //

CREATE TRIGGER trg_restar_saldo_beneficiario_abono
AFTER INSERT ON tblAbonos
FOR EACH ROW
BEGIN
    UPDATE tblBeneficiarios
    SET saldo = saldo - NEW.monto
    WHERE id_beneficiario = (SELECT id_beneficiario FROM tblPagos WHERE id_pago = NEW.id_pago);
END //

DELIMITER ;

DELIMITER //

CREATE TRIGGER trg_sumar_saldo_beneficiario_abono
BEFORE DELETE ON tblAbonos
FOR EACH ROW
BEGIN
    UPDATE tblBeneficiarios
    SET saldo = saldo + OLD.monto
    WHERE id_beneficiario = (SELECT id_beneficiario FROM tblPagos WHERE id_pago = OLD.id_pago);
END //

DELIMITER ;

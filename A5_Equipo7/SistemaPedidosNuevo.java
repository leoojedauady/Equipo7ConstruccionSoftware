/*
 * - procesarPedido ahora se divide en calcularDescuento, calcularCostoEnvio e 
 * imprimirResumen, cada uno con una sola responsabilidad.
 * - las validaciones (monto ≤ 0, cliente nulo, destino nulo) se revisan al 
 * inicio con return inmediato, eliminando el anidamiento en cascada de if/else.
 * - el tipo de cliente y el destino se evalúan con un switch (usando yield en 
 * el caso VIP, que tiene sub-condiciones), en vez de if-else anidados.
 * - ahora los CLIENTE_VIP se llaman VIP para que sea consistente
 *
 *
 * @author Dzul César
 * @author Lizama Edrei
 * @author Ojeda Leonardo
 */
public class SistemaPedidosNuevo {

    public static void main(String[] args) {
        SistemaPedidosNuevo sistema = new SistemaPedidosNuevo();
        sistema.procesarPedido("VIP", 1500.0, true, "MEXICO");
    }

    public void procesarPedido(String tipoCliente, double montoTotal, boolean esDiaEspecial, String destino) {
        // se valida primero y si es inválido y se sale temprano
        if (montoTotal <= 0) {
            System.out.println("El monto debe ser mayor a cero.");
            return;
        }
        if (tipoCliente == null) {
            System.out.println("Tipo de cliente inválido.");
            return;
        }
        if (destino == null) {
            System.out.println("Destino inválido.");
            return;
        }

        double descuento = calcularDescuento(tipoCliente, montoTotal, esDiaEspecial);
        double costoEnvio = calcularCostoEnvio(destino, montoTotal);
        double totalFinal = montoTotal - descuento + costoEnvio;

        //se cambió la bandera boolena a que lo haga directo
        if (totalFinal <= 0) {
            System.out.println("Error en la transacción.");
            return;
        }

        imprimirResumen(montoTotal, descuento, costoEnvio, totalFinal);
    }

    // calcula el descuento base según el tipo de cliente y monto,
    // y le suma el descuento adicional por día especial si aplica.
    private double calcularDescuento(String tipoCliente, double montoTotal, boolean esDiaEspecial) {
        double descuento = switch (tipoCliente) {
            case "REGULAR" -> {
                if (montoTotal > 1000) {
                    yield montoTotal * 0.05;
                } else {
                    yield 0.0;
                }
            }
            case "VIP" -> {
                if (montoTotal > 2000) {
                    yield montoTotal * 0.20;
                } else if (montoTotal > 1000) {
                    yield montoTotal * 0.15;
                } else {
                    yield montoTotal * 0.10;
                }
            }
            case "NUEVO" ->
                50.0;
            default ->
                0.0;
        };

        if (esDiaEspecial) {
            descuento = descuento + (montoTotal * 0.05);
        }

        return descuento;
    }

    // calcula el costo de envío según el destino y el monto total.
    private double calcularCostoEnvio(String destino, double montoTotal) {
        return switch (destino) {
            case "LOCAL" ->
                50.0;
            case "NACIONAL", "MEXICO" -> {
                if (montoTotal > 1500) {
                    yield 0.0;
                } else {
                    yield 150.0;
                }
            }

            case "INTERNACIONAL" ->
                (montoTotal > 3000) ? 100.0 : 500.0;
            default ->
                0.0;
        };
    }


    // imprime el resumen final de la compra aprobada.
    private void imprimirResumen(double montoTotal, double descuento, double costoEnvio, double totalFinal) {
        System.out.println("=== RESUMEN DE COMPRA===");
        System.out.println("Monto Base: $" + montoTotal);
        System.out.println("Descuento Aplicado: $" + descuento);
        System.out.println("Costo de Envio: $" + costoEnvio);
        System.out.println("Total a Pagar: $" + totalFinal);
        System.out.println("Estado: APROBADO");
    }
}

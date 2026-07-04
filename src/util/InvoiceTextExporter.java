package util;

import model.Invoice;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;

public class InvoiceTextExporter {

    public void exportInvoice(Invoice invoice) {
        if (invoice == null) {
            System.out.println("No invoice to export.");
            return;
        }

        String fileName = "invoice_" + invoice.getInvoiceId() + ".txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write("************ Paradise Hotel ************");
            writer.newLine();
            writer.write("Invoice ID: " + invoice.getInvoiceId());
            writer.newLine();
            writer.write("Reservation ID: " + invoice.getReservationId());
            writer.newLine();
            writer.write("Total Amount: " + formatAmount(invoice.getTotalAmount()));
            writer.newLine();
            writer.write("Payment Status: " + invoice.getPaymentStatus());
            writer.newLine();
            writer.write("Payment Date: " + 
                (invoice.getPaymentDate() != null 
                    ? invoice.getPaymentDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) 
                    : "Not Paid"));
            writer.newLine();
            writer.newLine();
            writer.write("Thank you for choosing Paradise Hotel!");
            writer.newLine();
            writer.write("****************************************");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String formatAmount(BigDecimal amount) {
        if (amount == null) return "0.00";
        return String.format("%.2f", amount);
    }
}

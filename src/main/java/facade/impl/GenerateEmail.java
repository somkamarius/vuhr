package facade.impl;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@SessionScoped
@Named
public class GenerateEmail implements Serializable {

    private transient CompletableFuture<String> randomNumberGenerationTask = null;
    private final Random random = new Random();

    public String generateEmail() {
        Map<String, String> requestParameters = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();

        randomNumberGenerationTask = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }

            int leftLimit = 97; // letter 'a'
            int rightLimit = 122; // letter 'z'
            int targetStringLength = 4;

            return random.ints(leftLimit, rightLimit + 1)
                    .limit(targetStringLength)
                    .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                    .toString();
        });

        return "/index?faces-redirect=true";
    }

    public boolean isEmailGenerationRunning() {
        return randomNumberGenerationTask != null && !randomNumberGenerationTask.isDone();
    }

    public String getEmailGenerationStatus() throws ExecutionException, InterruptedException {
        if (randomNumberGenerationTask == null) {
            return null;
        } else if (isEmailGenerationRunning()) {
            return "Email generation in progress";
        }
        return "Suggested email: " + randomNumberGenerationTask.get() + "@email.com";
    }
}

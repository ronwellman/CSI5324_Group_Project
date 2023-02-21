package baylor.csi5324.group_project.Domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Contract implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private boolean proofOfSignature;

    @NotNull(message="valid timestamp required")
    private LocalDate timestamp;

    @OneToOne
    private Job job;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @OneToOne
    private Payment payment;

    //Getters
    public boolean getProofOfSignature(){
        return proofOfSignature;
    }

    public LocalDate getTimestamp() {
        return timestamp;
    }

    public Job getJob(){
        return job;
    }

    public User getUser(){
        return user;
    }

    public Payment getPayment(){
        return payment;
    }

    // Setters
    public void setProofOfSignature(boolean isSigned){
        this.proofOfSignature = isSigned;
    }

    public void setTimestamp(LocalDate contractTimestamp){
        this.timestamp = contractTimestamp;
    }

    public void setJob(Job associatedJob){
        this.job = associatedJob;
    }

    public void setUser(User userUploadingContract){
        this.user = userUploadingContract;
    }

    public void setPayment(Payment contractPayment){
        this.payment = contractPayment;
    }

}

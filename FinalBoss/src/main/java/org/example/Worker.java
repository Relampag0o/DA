package org.example;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.util.Arrays;

public class Worker {
    private String full_name;
    private String jobTitle;
    private Address address;

    private Company company;

    private boolean isGlobal;
    private String[] ips;


     public Worker(String full_name, String jobTitle, Address address, Company company, boolean isGlobal, String[] ips) {
         this.full_name = full_name;
         this.jobTitle = jobTitle;
         this.address = address;
         this.company = company;
         this.isGlobal = isGlobal;
         this.ips = ips;
     }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public boolean isGlobal() {
        return isGlobal;
    }

    public void setGlobal(boolean global) {
        isGlobal = global;
    }

    public String[] getIps() {
        return ips;
    }

    public void setIps(String[] ips) {
        this.ips = ips;
    }

    public void toXML(Document doc){
         Element workers = doc.getDocumentElement();
         Element worker = doc.createElement("worker");
         workers.appendChild(worker);

         Element fullNameElement = doc.createElement("full_name");
         fullNameElement.appendChild(doc.createTextNode(this.full_name));
         worker.appendChild(fullNameElement);

        Element jobTitleElement = doc.createElement("jobTitle");
        jobTitleElement.appendChild(doc.createTextNode(this.jobTitle));
        worker.appendChild(jobTitleElement);

        Element addressesElement = doc.createElement("addresses");
        worker.appendChild(addressesElement);

        this.address.toXML(doc,addressesElement);

        this.company.toXML(doc,addressesElement);






    }
    @Override
    public String toString() {
        return "Worker{" +
                "full_name='" + full_name + '\'' +
                ", jobTitle='" + jobTitle + '\'' +
                ", address=" + address +
                ", company=" + company +
                ", isGlobal=" + isGlobal +
                ", ips=" + Arrays.toString(ips) +
                '}';
    }
}

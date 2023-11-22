# Overview
The Doctor Appointment Booking App is a web application designed to streamline the management of doctor appointments, patients, and related functionalities. Built using the Spring Boot framework in Java, the application incorporates various technologies to ensure efficiency and security.

# Features
### Controllers :

AdminController

    getAllPatients (GET): Retrieve a list of all patients.
    addDoctor (POST): Add a new doctor.
    getAllPatientsByBloodGroup (GET): Retrieve patients by blood group.

DoctorController

    getAllDoctors (GET): Retrieve a list of all doctors.
    getDoctorById (GET): Retrieve a doctor by ID.

PatientController

    patientSignUp (POST): Sign up a new patient.
    patientSignIn (POST): Sign in a patient.
    patientSignOut (DELETE): Sign out a patient.
    scheduleAppointment (POST): Schedule a new appointment.
    cancelAppointment (DELETE): Cancel an appointment.
    getDoctorsByQualificationOrSpec (GET): Retrieve doctors by qualification or specialization.

### Services :

AppointmentService 

    scheduleAppointment: Schedule a new appointment.
    cancelAppointment: Cancel an existing appointment.

DoctorService

    getAllDoctors: Retrieve a list of all doctors.
    addDoctor: Add a new doctor.
    getDoctorById: Retrieve a doctor by ID.
    getDoctorsByQualificationOrSpec: Retrieve doctors by qualification or specialization.

PatientService

    patientSignUp: Sign up a new patient.
    patientSignIn: Sign in a patient.
    patientSignOut: Sign out a patient.
    getAllPatients: Retrieve a list of all patients.
    getAllPatientsByBloodGroup: Retrieve patients by blood group.

PTokenService

    createToken: Generate a patient authentication token.
    deleteToken: Delete a patient authentication token.
    authenticate: Check if a patient is authenticated.

### Repository :

    IAppointmentRepo: Appointment entity repository.
    IDoctorRepo: Doctor entity repository.
    IPatientRepo: Patient entity repository.
    IPTokenRepo: PatientAuthenticationToken entity repository.

### Database Design :

Entities

    Doctor(
    docId (Integer)
    docName (String)
    docFee (Double)
    docSpecialization (Specialization - Enum)
    docQualification (Qualification - Enum)
    docContact (String)
    appointments (List<Appointment>)
    )


    Patient(
    patientId (Integer)
    patientName (String)
    patientEmail (String)
    patientPassword (String)
    patientGender (Gender - Enum)
    patientBloodGroup (BloopGroup - Enum)
    patientContact (String)
    patientDateOfBirth (LocalDateTime)
    appointments (List<Appointment>)
    )


    Appointment(
    appointmentId (Integer)
    appointmentDesc (String)
    appCreationTime (LocalDateTime)
    appScheduleTime (LocalDateTime)
    patient (Patient)
    doctor (Doctor)
    )


    PatientAuthenticationToken(
    tokenId (Integer)
    tokenValue (String)
    tokenCreationTime (LocalDateTime)
    patient (Patient)
    )


    Admin(
    adminId (Integer)
    adminName (String)
    adminEmail (String)
    adminPassword (String)
    )

### Data Structure
List

# Project Structure
    The project follows a standard Maven structure. Key components include:

    Controller Classes: AdminController, DoctorController, PatientController
    Service Classes: AppointmentService, DoctorService, PatientService, PTokenService
    Repository Interfaces: IAppointmentRepo, IDoctorRepo, IPatientRepo, IPTokenRepo
    Entity Classes: Doctor, Patient, Appointment,Admin,PatientAuthenticationToken
    Maven POM File: Configuration for dependencies.


# Summary
The Doctor Appointment Booking App is a web application developed using the Spring Boot framework in Java. This application streamlines the management of doctor appointments, patients, and related operations. Key features include patient authentication, appointment scheduling, and cancel Appointment. The technology stack involves Spring Boot, Swagger, Lombok, Spring Data JPA, and MySQL for data storage. The application also integrates email services for communication and employs password encryption for enhanced security. The project is well-organized, following the Maven structure.    
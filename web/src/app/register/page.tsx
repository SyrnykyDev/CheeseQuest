// *** COMPONENTS ***
import React from "react";
import SansWrapper from "@/components/SansWrapper/SansWrapper";
import Button from "@/components/Button/Button";
import Input from "@/components/Input/Input";
import FormikWrapper from "@/app/register/formikWrapper";

// *** ASSETS ***
import googleSVG from "../../../public/google-icon-logo-svgrepo-com.svg";
import Image from "next/image";

const Page = () => {
  return (
    // <div>
    // <FormikWrapper>
    <SansWrapper centered>
      <h1 style={{ color: "black" }}>Register account</h1>

      <Input placeholder={"Enter your email"} name={"email"} required />
      <Input placeholder={"Enter your nickname"} name={"nickname"} required />
      <Input
        placeholder={"Enter your password"}
        name={"password"}
        type={"password"}
        required
      />

      <div
        style={{
          display: "flex",
          justifyContent: "center",
          gap: "10px",
          alignItems: "center",
        }}
      >
        <Button buttonColor={"blue"}>Log In</Button>
        <b>-</b>
        <Button buttonColor={"green"}>Register</Button>
      </div>
      <span style={{ color: "black", fontSize: "22px", fontWeight: 700 }}>
        {" "}
        or
      </span>
      <Button buttonColor={"gray"}>
        <Image src={googleSVG} alt={"Google Icon"} width={30} />
        Register with Google
      </Button>
    </SansWrapper>
    // </FormikWrapper>
    // </div>
  );
};

export default Page;

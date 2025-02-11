// *** COMPONENTS ***
import React from "react";
import SansWrapper from "@/components/SansWrapper/SansWrapper";
import Button from "@/components/Button/Button";
import Input from "@/components/Input/Input";

// *** ASSETS ***
import googleSVG from "../../../public/google-icon-logo-svgrepo-com.svg";
import Image from "next/image";
import MotionWrapper from "@/components/MotionWrapper/MotionWrapper";
import Link from "next/link";

const Page = () => {
  return (
    // <div>
    <MotionWrapper>
      <SansWrapper centered>
        <h1 style={{ color: "black" }}>Sign In</h1>
        <Button buttonColor={"gray"}>
          <Image src={googleSVG} alt={"Google Icon"} width={30} />
          Sign in with Google
        </Button>
        <span style={{ color: "black", fontSize: "22px", fontWeight: 700 }}>
          {" "}
          or
        </span>
        <Input placeholder={"Enter your email"} />
        <Button buttonColor={"gray"}>Continue with email</Button>

        <Link href={"/register"}>
          <Button buttonColor={"blue"}>Register</Button>
        </Link>
      </SansWrapper>
    </MotionWrapper>
    // </div>
  );
};

export default Page;

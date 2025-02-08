// *** COMPONENTS ***
import React from "react";
import Image from "next/image";

// *** STYLES ***
import styles from "./header.module.scss";

// *** ASSETS ***
import userIMG from "../../../public/header/user.svg";
import logoSVG from "../../../public/header/logo.svg";
import Link from "next/link";
import Button from "@/components/Button/Button";

const Header = () => {
  return (
    <>
      <header className={styles.header}>
        <Link href="/">
          <Image src={logoSVG} alt={"Logo"} />
        </Link>
        <div className={styles.header_rightPart}>
          <Button>Sign In</Button>
          <Link href={"/login"}>
            <Button buttonColor={"blue"}>Log In</Button>
          </Link>
          <Button buttonColor={"orange"}>Log In</Button>
          {/*<Button>Sign In</Button>*/}
        </div>
        <div>
          <Link href="/profile">
            {/*<Image src={userIMG} alt={"User Img"} width={30} height={30} />*/}
          </Link>
        </div>
      </header>
      <div className={styles.mockContainer} />
    </>
  );
};

export default Header;

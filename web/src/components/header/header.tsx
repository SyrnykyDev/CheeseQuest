// *** COMPONENTS ***
import React from "react";
import Image from "next/image";

// *** STYLES ***
import styles from "./header.module.scss";

// *** ASSETS ***
import userIMG from "../../../public/header/user.svg";
import Link from "next/link";

const Header = () => {
  return (
    <>
      <header className={styles.header}>
        <Link href="/">LOGO</Link>

        <div>
          <Link href="/profile">
            <Image src={userIMG} alt={"User Img"} width={30} height={30} />
          </Link>
        </div>
      </header>
      <div className={styles.mockContainer} />
    </>
  );
};

export default Header;

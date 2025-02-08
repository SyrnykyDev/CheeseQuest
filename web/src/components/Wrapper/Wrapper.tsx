import React, { ReactNode } from "react";
import Header from "@/components/header/header";
import Footer from "@/components/Footer/Footer";

// *** STYLES ***
import styles from "./wrapper.module.scss";

interface WrapperProps {
  children: ReactNode;
  header?: boolean;
  footer?: boolean;
}

/**
 * This component will appear in every page
 * @param children
 * @param header
 * @param footer
 * @constructor
 */
const Wrapper = ({ children, header = true, footer = true }: WrapperProps) => {
  return (
    <div className={styles.wrapper}>
      {header && <Header />}
      <div className={styles.wrapper_content}>{children}</div>
      {footer && <Footer />}
    </div>
  );
};

export default Wrapper;

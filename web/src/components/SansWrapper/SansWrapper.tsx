import React, { ReactNode } from "react";

// *** STYLES ***
import styles from "./SansWrapper.module.scss";

interface ISansWrapperProps {
  children: ReactNode;
  centered?: boolean;
}

/**
 * This Wrapper will wrap and have styles like Sans
 * @param children
 * @param centered
 * @constructor
 */
const SansWrapper = ({ children, centered }: ISansWrapperProps) => {
  return (
    <div
      className={`${styles.sansWrapper} ${centered ? styles.sansWrapper_center : ""}`}
    >
      {children}
    </div>
  );
};

export default SansWrapper;

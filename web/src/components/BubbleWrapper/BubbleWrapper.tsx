import React, { ReactNode } from "react";
import styles from "./BubbleWrapper.module.scss";

const BubbleWrapper = ({
  children,
  centered = true,
}: {
  children: ReactNode;
  centered?: boolean;
}) => {
  return (
    <div
      className={`${styles.bubbleWrapper} ${centered ? styles.bubbleWrapper_centered : ""}`}
    >
      {children}
    </div>
  );
};

export default BubbleWrapper;

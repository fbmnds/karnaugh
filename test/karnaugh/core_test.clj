(ns karnaugh.core-test
  (:use clojure.test
        karnaugh.core))


(def k1 #{#{'a 'B 'C 'd}
          #{'A 'b 'c 'd}
          #{'A 'b 'c 'D}
          #{'A 'b 'C 'd}
          #{'A 'b 'C 'D}
          #{'A 'B 'c 'd}
          #{'A 'B 'c 'D}
          #{'A 'B 'C 'd}})


(def k2 #{#{'A 'B 'C 'D}
          #{'A 'B 'C 'd}})


(def k3 #{#{'a 'b 'c 'd}
          #{'a 'B 'c 'd}
          #{'a 'b 'c 'D}
          #{'a 'B 'c 'D}
          #{'A 'B 'C 'd}
          #{'A 'B 'C 'D}
          #{'A 'b 'C 'd}
          #{'A 'b 'C 'D}})


(def k4 #{#{'a 'b 'c}
          #{'a 'B 'c}
          #{'a 'b 'C}
          #{'a 'B 'C}})


(def k5 #{#{'a 'B 'c 'd}
          #{'A 'B 'c 'D}
          #{'A 'b 'C 'D}
          #{'a 'b 'c 'D}
          #{'a 'B 'C 'D}
          #{'A 'B 'C 'd}})


(def k6 #{#{'a 'b 'c 'd}
          #{'a 'B 'c 'd}
          #{'A 'B 'c 'd}
          #{'a 'b 'c 'D}
          #{'a 'B 'c 'D}
          #{'A 'B 'c 'D}})


(def k7 #{#{'a 'B 'c 'd}
          #{'A 'B 'c 'd}
          #{'a 'b 'c 'D}
          #{'a 'b 'C 'D}
          #{'A 'b 'c 'D}
          #{'A 'b 'C 'D}
          #{'a 'B 'C 'd}
          #{'A 'B 'C 'd}})


(def k8 #{#{'a 'b 'c 'd}
          #{'A 'b 'c 'd}
          #{'a 'B 'c 'D}
          #{'A 'B 'c 'D}
          #{'a 'B 'C 'D}
          #{'A 'B 'C 'D}
          #{'a 'b 'C 'd}
          #{'A 'b 'C 'd}})


(deftest karnaugh-test
  (testing "karnaugh.core-test failed."
    (is (= (karnaugh k1)
           #{#{'A 'c}
             #{'A 'b}
             #{'B 'C 'd}}))

    (is (= (karnaugh k2)
           #{#{'A 'B 'C}}))

    (is (= (karnaugh k3)
           #{#{'a 'c}
             #{'A 'C}}))

    (is (= (karnaugh k4)
           #{#{'a}}))

    (is (= (karnaugh k5)
           #{#{'a 'B 'c 'd}
             #{'A 'B 'c 'D}
             #{'A 'b 'C 'D}
             #{'a 'b 'c 'D}
             #{'a 'B 'C 'D}
             #{'A 'B 'C 'd}}))

    (is (= (karnaugh k6)
           #{#{'a 'c}
             #{'B 'c}}))

    (is (= (karnaugh k7)
           #{#{'B 'd}
             #{'b 'D}}))

    (is (= (karnaugh k8)
           #{#{'B 'D}
             #{'b 'd}}))))

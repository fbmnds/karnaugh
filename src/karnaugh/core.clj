(ns karnaugh.core)


;; (defn reduce-sets [s1 s2]
;;   (let [intersection-s1-s2 (clojure.set/intersection s1 s2)]
;;     (if (empty? intersection-s1-s2)
;;       [s1 s2]
;;       intersection-s1-s2)))
;;
;;
;; (defn r [x]
;;   (flatten (map #(reduce-sets (first %) (last %)) x)))
;;

;; [#{#{x1, x2, x3, x4}, ...}, #{y1, y2}] -> count



(fn karnaugh [bool-fn]
  (letfn [(count-minterm
            [bool-fn minterm]
            (reduce + (map #(if (clojure.set/subset? minterm %) 1 0) bool-fn)))
          (get-minterm-4
            [bool-fn]
            (set (map second
                      (filter #(= (first %) 4)
                              (for [m [['a 'c] ['A 'c] ['a 'C] ['A 'C]
                                       ['a 'b] ['a 'B] ['A 'b] ['A 'B]
                                       ['c 'd] ['c 'D] ['C 'd] ['C 'D]
                                       ['b 'd] ['B 'd] ['b 'D] ['B 'D]
                                       ['b 'c] ['B 'c] ['b 'C] ['B 'C]]]
                                [(count-minterm bool-fn m) m])))))
          (contains-minterm?
            [minterm-set bool-fn-set]
            (reduce #(or %1
                         (clojure.set/subset? %2 bool-fn-set)) false minterm-set))
          (reduce-by-minterm-4
            [bool-fn]
            (filter #(not (contains-minterm? (get-minterm-4 bool-fn) %)) bool-fn))
          (get-minterm-2
            [bool-fn]
            (set (map second
                      (filter #(= (first %) 2)
                              (for [m [['a 'c 'd] ['A 'c 'd]
                                       ['a 'c 'D] ['A 'c 'D]
                                       ['a 'C 'd] ['A 'C 'd]
                                       ['a 'C 'D] ['A 'C 'D]
                                       ['a 'b 'c] ['a 'B 'c]
                                       ['A 'b 'c] ['A 'B 'c]
                                       ['a 'b 'C] ['a 'B 'C]
                                       ['A 'b 'C] ['A 'B 'C]]]
                                [(count-minterm bool-fn m) m])))))
          (reduce-by-minterm-2
            [bool-fn]
            (filter #(not (contains-minterm? (get-minterm-2 bool-fn) %)) bool-fn))]
    (let [bool-fn-reduced-by-minterms (reduce-by-minterm-2 (reduce-by-minterm-4 bool-fn))
          reduced-bool-fn (if (empty? bool-fn-reduced-by-minterms)
                            (set
                             (filter #(not (empty? %))
                                     (clojure.set/union
                                      (map set (get-minterm-4 bool-fn))
                                      (map set (get-minterm-2
                                                (reduce-by-minterm-4 bool-fn))))))
                            (set
                             (filter #(not (empty? %))
                                     (clojure.set/union
                                      (map set (get-minterm-4 bool-fn))
                                      (map set (get-minterm-2
                                                (reduce-by-minterm-4 bool-fn)))
                                      (conj #{}
                                            (apply
                                             clojure.set/intersection
                                             bool-fn-reduced-by-minterms))))))]
      (if (empty? reduced-bool-fn)
        bool-fn
        reduced-bool-fn))))

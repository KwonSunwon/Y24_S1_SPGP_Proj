package kr.ac.tukorea.ge.spgp.ksw.spaceclicker;

public interface GameObject {
    default void Init() {};
    default void Render() {};
    default void Update() {};
}

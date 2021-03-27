package jpabook.jpashop.domain;

//@SpringBootTest
//@Transactional
//class MemberRepositoryTest {
//    @Autowired MemberRepository memberRepository;
//
//    @Test
//    @Rollback(false) // 처음보는 애노테이션.
//    public void testMember() throws Exception {
//        //given
//        Member member = new Member();
//        member.
//        member.setUsername("jdk");
//        //when
//        Long savedMember = memberRepository.save(member);
//        Member findMember = memberRepository.find(savedMember);
//        //then
//        Assertions.assertThat(findMember.getId()).isEqualTo(member.getId());
//        Assertions.assertThat(findMember.getUsername()).isEqualTo(member.getUsername());
//        Assertions.assertThat(findMember).isEqualTo(member);
//    }
//
//}
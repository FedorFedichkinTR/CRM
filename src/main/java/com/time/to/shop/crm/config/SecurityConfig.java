//package com.time.to.shop.crm.configs;
//
//import com.epam.edp.model.db.Privilege;
//import com.epam.edp.model.rest.CustomAccessDeniedHandler;
//import com.epam.edp.model.rest.JsonAuthenticationFailureHandler;
//import com.epam.edp.model.rest.JsonAuthenticationSuccessHandler;
//import com.epam.edp.model.rest.JsonLogoutSuccessHandler;
//import com.epam.edp.security.EdpUserDetailsService;
//import com.epam.edp.security.RestAuthenticationEntryPoint;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.env.Environment;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.access.AccessDeniedHandler;
//import org.springframework.security.web.authentication.AuthenticationFailureHandler;
//import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
//import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
//
//import javax.annotation.PostConstruct;
//
//import static com.epam.edp.constants.Endpoints.*;
//import static com.epam.edp.model.db.Privilege.*;
//import static org.springframework.http.HttpMethod.*;
//
//@Configuration
//@ComponentScan(basePackages = {"com.epam.edp.security"})
//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Autowired
//    private EdpUserDetailsService userDetailsService;
//
//    @Autowired
//    private Environment environment;
//
//    @PostConstruct
//    private void setSecurityContextStrategy() {
//        SecurityContextHolder.setStrategyName(SecurityContextHolder.MODE_INHERITABLETHREADLOCAL);
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .csrf()
//                .disable()
//
//                .rememberMe()
//                .tokenValiditySeconds(Integer.parseInt(
//                    environment.getRequiredProperty("edp.login.remember-me.cookie.validity-seconds")))
//                .rememberMeParameter(environment.getRequiredProperty("edp.login.remember-me.cookie.name"))
//                .and()
//
//                .authorizeRequests()
//                .antMatchers(CSS, JS, MANIFEST, ICON, API_VERSION).permitAll()
//
//                //REGISTRATION:
//                .antMatchers(GET, PAGE_USER_ACTIVATION).permitAll()
//                .antMatchers(GET, PAGE_USER_REGISTRATION).permitAll()
//                .antMatchers(POST, PAGE_USER_REGISTRATION).permitAll()
//
//                //PASSWORD UPDATE
//                .antMatchers(POST, API_USERS_UPDATE_PASSWORD).permitAll()
//
//                //PASSWORD RECOVERY
//                .antMatchers(GET, PAGE_FORGOT_PASSWORD).permitAll()
//                .antMatchers(GET, PAGE_CHANGE_PASSWORD).permitAll()
//                .antMatchers(POST, API_PASSWORD_RESET).permitAll()
//
//                //ERROR PAGE:
//                .antMatchers(GET, PAGE_ERROR).permitAll()
//
//                //PUBLIC PAGES
//                .antMatchers(GET,
//                        "/",
//                        PAGE_COMPARE,
//                        PAGE_REPORTS,
//                        PAGE_SETTINGS,
//                        PAGE_SERVERS,
//                        PAGE_SOURCES,
//                        PAGE_EVENTLOG,
//                        PAGE_SINGLE_REPORT,
//                        PAGE_REPORTS_COMPARE,
//                        API_PARSERS,
//                        API_LOG_FILE)
//                .authenticated()
//
//                //SWAGGER
//                .antMatchers(WEBJARS_SPRINGFOX_SWAGGER,
//                        SWAGGER_URL,
//                        SWAGGER_URL_REDIRECT,
//                        SWAGGER_RESOURCES,
//                        SWAGGER_DOCUMENTATION).hasAuthority(str(PRIV_SOURCES_READ))
//
//                //REPORTS:
//                .antMatchers(GET, API_REPORTS, API_SEARCH_REPORT + "/**")
//                .hasAnyAuthority(str(PRIV_REPORTS_READ_ANY), str(PRIV_REPORTS_READ))
//                .antMatchers(GET, API_REPORTS_SEARCH)
//                .hasAnyAuthority(str(PRIV_REPORTS_READ_ANY), str(PRIV_REPORTS_READ))
//                .antMatchers(GET, API_REPORTS_COMPARE)
//                .hasAuthority(str(PRIV_REPORTS_COMPARE))
//                .antMatchers(POST, API_REPORTS, API_GENERATE_REPORT)
//                .hasAuthority(str(PRIV_REPORTS_CREATE))
//                .antMatchers(POST, API_REPORTS_UPDATE_SHARED)
//                .hasAnyAuthority(str(PRIV_REPORTS_UPDATE), str(PRIV_REPORTS_UPDATE_ANY))
//                .antMatchers(PUT, API_CERTAIN_REPORT)
//                .hasAuthority(str(PRIV_REPORTS_CREATE))
//                .antMatchers(PATCH, API_CERTAIN_REPORT)
//                .hasAuthority(str(PRIV_REPORTS_CREATE))
//                .antMatchers(DELETE, API_CERTAIN_REPORT)
//                .hasAnyAuthority(str(PRIV_REPORTS_DELETE_ANY), str(PRIV_REPORTS_DELETE))
//
//                //EVENTS:
//                .antMatchers(GET, API_EVENTS, API_CERTAIN_EVENT).hasAuthority(str(PRIV_EVENTS_READ))
//                .antMatchers(DELETE, API_CERTAIN_EVENT).hasAuthority(str(PRIV_EVENTS_DELETE))
//                .antMatchers(GET, API_LOG_FILE).hasAuthority(str(PRIV_EVENTS_FULL_READ))
//
//                //SERVER:
//                .antMatchers(GET, API_SERVERS, API_CERTAIN_SERVER, API_SEARCH_SERVERS + "/**")
//                .hasAuthority(str(PRIV_SERVERS_READ))
//                .antMatchers(POST, API_SERVERS_COMPARE)
//                .hasAuthority(str(PRIV_SERVERS_COMPARE))
//                .antMatchers(POST, API_SERVERS)
//                .hasAuthority(str(PRIV_SERVERS_CREATE))
//                .antMatchers(PUT, API_CERTAIN_SERVER)
//                .hasAuthority(str(PRIV_SERVERS_CREATE))
//                .antMatchers(PATCH, API_CERTAIN_SERVER)
//                .hasAuthority(str(PRIV_SERVERS_UPDATE))
//                .antMatchers(DELETE, API_CERTAIN_SERVER)
//                .hasAuthority(str(PRIV_SERVERS_DELETE))
//                .antMatchers(POST, API_CONNECTION_TEST)
//                .hasAuthority(str(PRIV_CONNECTIONS_TEST))
//                .antMatchers(POST, API_CONNECTIONS_TEST)
//                .hasAuthority(str(PRIV_CONNECTIONS_TEST))
//
//                //CONNECTIONS:
//                .antMatchers(GET, API_CONNECTIONS_FOR_SERVER + "/**").hasAuthority(str(PRIV_CONNECTIONS_READ))
//
//                //SETTINGS:
//                .antMatchers(GET, API_SETTINGS, API_CERTAIN_SETTING, API_SEARCH_SETTINGS + "/**")
//                .hasAuthority(str(PRIV_SETTINGS_READ))
//                .antMatchers(POST, API_SETTINGS).hasAuthority(str(PRIV_SETTINGS_CREATE))
//                .antMatchers(PUT, API_CERTAIN_SETTING).hasAuthority(str(PRIV_SETTINGS_CREATE))
//                .antMatchers(PATCH, API_CERTAIN_SETTING).hasAuthority(str(PRIV_SETTINGS_UPDATE))
//                .antMatchers(DELETE, API_CERTAIN_SETTING).hasAuthority(str(PRIV_SETTINGS_DELETE))
//
//                //SOURCES:
//                .antMatchers(GET, API_SOURCES, API_CERTAIN_SOURCE, API_SEARCH_SOURCES + "/**")
//                .hasAuthority(str(PRIV_SOURCES_READ))
//                .antMatchers(POST, API_SOURCES).hasAuthority(str(PRIV_SOURCES_CREATE))
//                .antMatchers(PUT, API_CERTAIN_SOURCE).hasAuthority(str(PRIV_SOURCES_CREATE))
//                .antMatchers(PATCH, API_CERTAIN_SOURCE).hasAuthority(str(PRIV_SOURCES_UPDATE))
//                .antMatchers(DELETE, API_CERTAIN_SOURCE).hasAuthority(str(PRIV_SOURCES_DELETE))
//
//                //USERS:
//                .antMatchers(GET, API_SEARCH_USER).hasAuthority(str(PRIV_USERS_READ))
//                .antMatchers(POST, API_USERS).hasAuthority(str(PRIV_USERS_CREATE))
//                .antMatchers(POST, API_USER_UPDATE_ENABLED, API_USER_SET_ROLE).hasAuthority(str(PRIV_USERS_UPDATE))
//                .antMatchers(GET, API_AUTHENTICATED_USER_INFO).authenticated()
//                .antMatchers(GET, API_CERTAIN_USER_STATISTIC).hasAuthority(str(PRIV_USERS_READ))
//                .antMatchers(GET, PAGE_USERS).hasAuthority(str(PRIV_USERS_READ))
//                .antMatchers(DELETE, API_CERTAIN_USER).hasAuthority(str(PRIV_USERS_DELETE))
//
//                //ROLES:
//                .antMatchers(GET, API_ROLES, API_ROLES_LIST).hasAuthority(str(PRIV_ROLES_READ))
//                .antMatchers(POST, API_ROLES_SET_DEFAULT).hasAuthority(str(PRIV_ROLES_SET_DEFAULT))
//                .antMatchers(DELETE, API_CERTAIN_ROLE)
//                .hasAnyAuthority(str(PRIV_ROLES_DELETE), str(PRIV_ROLES_UPDATE))
//                .antMatchers(POST, API_ROLES).hasAuthority(str(PRIV_ROLES_CREATE))
//
//                //API_FEEDBACK
//                .antMatchers(POST, API_FEEDBACK).hasAuthority(PRIV_FEEDBACK_CREATE.toString())
//
//                //PREFERENCES
//                .antMatchers(GET, API_PREFERENCES).hasAnyAuthority(str(PRIV_PREFERENCES_READ), str(PRIV_PREFERENCES_READ_GLOBAL))
//
//                //PRIVILEGES:
//                .antMatchers(GET, API_PRIVILEGES).hasAuthority(str(PRIV_PRIVILEGES_READ))
//
//                //ELSE:
//                .anyRequest().authenticated()
//                .and()
//
//                .formLogin()
//                .loginPage(PAGE_LOGIN)
//                .successHandler(authenticationSuccessHandler())
//                .failureUrl(PAGE_LOGIN)
//                .permitAll()
//                .failureHandler(authenticationFailureHandler())
//                .and()
//                .exceptionHandling().accessDeniedHandler(accessDeniedHandler())
//                                    .defaultAuthenticationEntryPointFor(new RestAuthenticationEntryPoint(),
//                                        new AntPathRequestMatcher(API_PREFIX + "/**"))
//
//                .and()
//                .logout()
//                .deleteCookies("JSESSIONID")
//                .logoutSuccessHandler(logoutSuccessHandler());
//    }
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService)
//                .passwordEncoder(encoder());
//    }
//
//    @Bean
//    public PasswordEncoder encoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public AuthenticationSuccessHandler authenticationSuccessHandler() {
//        return new JsonAuthenticationSuccessHandler();
//    }
//
//    @Bean
//    public AuthenticationFailureHandler authenticationFailureHandler() {
//        return new JsonAuthenticationFailureHandler();
//    }
//
//    @Bean
//    public AccessDeniedHandler accessDeniedHandler() {
//        return new CustomAccessDeniedHandler();
//    }
//
//    @Bean
//    public LogoutSuccessHandler logoutSuccessHandler() {
//        return new JsonLogoutSuccessHandler();
//    }
//
//    private String str(Privilege privilege) {
//        return privilege.toString();
//    }
//}
